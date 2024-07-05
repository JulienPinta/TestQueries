package org.example.testspringdata.data.asso1;

import com.mysema.commons.lang.CloseableIterator;
import com.mysema.commons.lang.IteratorAdapter;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.QueryHandler;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.example.testspringdata.data.asso1.QUser;
import org.example.testspringdata.data.asso1.QGroup;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static com.querydsl.jpa.JPQLTemplates.DEFAULT_ESCAPE;

public class UserRepository extends SimpleJpaRepository<User, Long> {

  protected final Querydsl querydsl;
  protected final EntityPathBase<User> path;
  protected final JPAQueryFactory jpaQueryFactory;
  private final QUser user = QUser.user;
  private final QGroup group = QGroup.group;

  public UserRepository(EntityManager em, Class<User> classType) {
    super(classType, em);
    this.querydsl = new Querydsl(em, new PathBuilderFactory().create(classType));
    this.path = new EntityPathBase<User>(classType, classType.getTypeName());
    this.jpaQueryFactory =
        new JPAQueryFactory(
            new JPQLTemplates(
                DEFAULT_ESCAPE,
                new QueryHandler() {

                  @Override
                  public void addEntity(Query query, String alias, Class<?> type) {
                    // do nothing
                  }

                  @Override
                  public void addScalar(Query query, String alias, Class<?> type) {
                    // do nothing
                  }

                  @Override
                  public boolean createNativeQueryTyped() {
                    return true;
                  }

                  @Override
                  @SuppressWarnings("unchecked")
                  public <T> CloseableIterator<T> iterate(
                      Query query, final FactoryExpression<?> projection) {
                    Stream<T> stream = stream(query, projection);
                    return new IteratorAdapter<T>(stream.iterator(), stream::close);
                  }

                  @Override
                  public <T> Stream<T> stream(Query query, FactoryExpression<?> projection) {
                    final Stream resultStream = query.getResultStream();
                    if (projection != null) {
                      return resultStream.map(
                          element ->
                              projection.newInstance(
                                  (Object[])
                                      (element.getClass().isArray()
                                          ? element
                                          : new Object[] {element})));
                    }
                    return resultStream;
                  }

                  @Override
                  public boolean transform(Query query, FactoryExpression<?> projection) {
                    return false;
                  }

                  @Override
                  public boolean wrapEntityProjections() {
                    return false;
                  }
                }) {},
            em);
  }

  public List<GroupAndUsers> getGroupAndUsers() {
    return jpaQueryFactory
        .select(user, group)
        .from(group)
        .join(user)
        .on(user.groupId.eq(group.id))
        .transform(groupBy(group.id).as(group, list(user)))
        .values()
        .stream()
        .map(groups -> new GroupAndUsers(groups.getOne(group), groups.getList(user)))
        .collect(Collectors.toList());
  }
}

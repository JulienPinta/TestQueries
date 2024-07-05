package org.example.testspringdata.data.asso3;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomChairRepositoryImpl implements CustomChairRepository{
    private final JPAQueryFactory queryFactory;

    public CustomChairRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

}

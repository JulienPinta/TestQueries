package org.example.testspringdata.data.asso3;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.example.testspringdata.data.asso3.QRoom;
import org.example.testspringdata.data.asso3.QChair;

@Repository
public class CustomRoomRepositoryImpl implements CustomRoomRepository{
    private final JPAQueryFactory queryFactory;
    private final QRoom room = QRoom.room;
    private final QChair chair = QChair.chair;

    public CustomRoomRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Room> getRoomsOneRequest() {
        return queryFactory.selectFrom(room)
                .join(room.chairs,chair).fetchJoin().fetch();
    }
}

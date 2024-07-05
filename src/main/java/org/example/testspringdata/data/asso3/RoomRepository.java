package org.example.testspringdata.data.asso3;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface RoomRepository
        extends JpaRepository<Room, Long>, QuerydslPredicateExecutor<Room>, CustomRoomRepository {

    @EntityGraph(attributePaths = {"chairs"})
    @Query(
            """
            SELECT r
              FROM Room r
              JOIN r.chairs ch
            """)
    List<Room> getAllAnotherOneRequest();
}

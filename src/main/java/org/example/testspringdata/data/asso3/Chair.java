package org.example.testspringdata.data.asso3;

import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryType;
import jakarta.persistence.*;
import org.example.testspringdata.data.asso3.Room;

@Entity
@Table(name = "chair")
public class Chair {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Room.class)
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
    private Room room;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}

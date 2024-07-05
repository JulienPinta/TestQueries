package org.example.testspringdata.data.asso3;

import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryType;
import jakarta.persistence.*;
import org.example.testspringdata.data.asso2.City;

import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "room", fetch = LAZY, cascade = ALL)
    private Set<Chair> chairs;

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

    public Set<Chair> getChairs() {
        return chairs;
    }

    public void setChairs(Set<Chair> chairs) {
        this.chairs = chairs;
    }
}

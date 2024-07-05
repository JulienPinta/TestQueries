package org.example.testspringdata.data.asso2;

import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryType;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @QueryType(PropertyType.NONE)
    @OneToMany(mappedBy = "country", cascade = ALL, fetch = EAGER)
    private Set<City> cities;

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

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }
}

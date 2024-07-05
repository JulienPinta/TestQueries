package org.example.testspringdata.data.asso2;

import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryType;
import jakarta.persistence.*;

@Entity
@Table(name = "city")
public class City {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @QueryType(PropertyType.NONE)
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Country.class)
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
    private Country country;

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}

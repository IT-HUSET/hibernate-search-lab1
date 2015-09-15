package se.ithuset.hibsearch.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: Per-Ingemar Andersson, It-huset i Norden AB
 */
@SuppressWarnings("unused")
@Entity
public class Beer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull
    private Long beerId;

    @Field
    private String name;

    @Field
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "beers", fetch = FetchType.LAZY)
    @ContainedIn
    private Set<Bar> bars = new HashSet<>();

    public Beer() {
    }

    public Beer(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getBeerId() {
        return beerId;
    }

    public void setBeerId(Long beerId) {
        this.beerId = beerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Bar> getBars() {
        return bars;
    }

    public void setBars(Set<Bar> bars) {
        this.bars = bars;
    }
}

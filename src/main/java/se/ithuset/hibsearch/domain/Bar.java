package se.ithuset.hibsearch.domain;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

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
@Indexed
public class Bar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull
    private Long barId;

    @Field
    private String name;

    @Field
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @IndexedEmbedded(depth = 1)
    private Address address;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "BAR_P_BEER",
            joinColumns = {@JoinColumn(name = "bar_id")},
            inverseJoinColumns = {@JoinColumn(name = "beer_id")})
    @IndexedEmbedded(depth = 1)
    private Set<Beer> beers = new HashSet<>();

    private int rating;

    public Bar() {
    }

    public Bar(String name, String description, int rating) {
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    public Long getBarId() {
        return barId;
    }

    public void setBarId(Long barId) {
        this.barId = barId;
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

    public Address getAddress() {
        return address;
    }

    public Set<Beer> getBeers() {
        return beers;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void addBeer(Beer beer) {
        this.getBeers().add(beer);
        beer.getBars().add(this);
    }

    public void addAddress(Address address) {
        this.address = address;
        address.getBars().add(this);
    }
}

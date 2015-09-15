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
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull
    private Long addressId;

    @Field
    private String addressRow;

    private String zipCode;

    @Field
    private String city;

    @JsonIgnore
    @OneToMany(mappedBy = "address")
    @ContainedIn
    private Set<Bar> bars = new HashSet<>();

    public Address() {
    }

    public Address(String addressRow, String zipCode, String city) {
        this.addressRow = addressRow;
        this.zipCode = zipCode;
        this.city = city;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddressRow() {
        return addressRow;
    }

    public void setAddressRow(String addressRow) {
        this.addressRow = addressRow;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Bar> getBars() {
        return bars;
    }
}

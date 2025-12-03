package com.caddospace.caddoaccommodationhub.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "PROPERTY")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Property extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROPERTY_ID")
    private long propertyId;

    @Column(name = "NAME")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_ID")
    private PropertyType type;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "ZIP_CODE")
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private Users users;

    @OneToMany(mappedBy = "property")
    private List<Room> rooms = new ArrayList<>();

    //    Booking
    @OneToMany(mappedBy = "property")
    private List<Booking> bookings = new ArrayList<>();

    //    vacation details
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private VacationRentalDetails vacationRentalDetails;
}

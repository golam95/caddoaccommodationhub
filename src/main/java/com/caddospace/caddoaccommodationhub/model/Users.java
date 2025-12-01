package com.caddospace.caddoaccommodationhub.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "USERS")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Users extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "PHONE")
    private String phone;

    // Owning side of relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private UserRole userRole;

//    @OneToMany(mappedBy = "users")
    @OneToMany(mappedBy = "users")
    private List<Property> properties= new ArrayList<>();

//    Booking
    @OneToMany(mappedBy = "users")
    private List<Booking> bookings = new ArrayList<>();

}

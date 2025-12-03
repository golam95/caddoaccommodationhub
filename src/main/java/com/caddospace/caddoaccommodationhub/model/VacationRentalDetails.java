package com.caddospace.caddoaccommodationhub.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Entity
@Table(name = "VACATION_RENTAL_DETAILS")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class VacationRentalDetails extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RENTAL_ID")
    private long rentalId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROPERTY_ID", nullable = false, unique = true)
    private Property property;

    @Column(name = "MIN_STAY_DAYS", nullable = false)
    private int minStayDays = 1;

    @Column(name = "MAX_GUESTS", nullable = false)
    private int maxGuests;

    @Column(name = "CLEANING_FEE", nullable = false)
    private double cleaningFee;
}

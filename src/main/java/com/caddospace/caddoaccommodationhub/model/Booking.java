package com.caddospace.caddoaccommodationhub.model;

import com.caddospace.caddoaccommodationhub.Enum.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "BOOKING")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKING_ID")
    private long bookingId;

    // ------------ RELATIONS ---------------

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROPERTY_ID", nullable = false)
    private Property property;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID", nullable = false)
    private Room room;

    // ------------ FIELDS ---------------
    @Column(name = "CHECK_IN_DATE", nullable = false)
    private LocalDate checkInDate;

    @Column(name = "CHECK_OUT_DATE", nullable = false)
    private LocalDate checkOutDate;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private double totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private BookingStatus bookingStatus = BookingStatus.PENDING;
}

package com.caddospace.caddoaccommodationhub.model;

import com.caddospace.caddoaccommodationhub.Enum.RoomTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Entity
@Table(name = "ROOM")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Room extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID")
    private long roomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROPERTY_ID")
    private Property property;

    @Column(name = "ROOM_NUMBER", nullable = false)
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private RoomTypeEnum type;

    @Column(name = "CAPACITY", nullable = false)
    private int capacity;

    @Column(name = "PRICE_PER_NIGHT", nullable = false)
    private double pricePerNight;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AMENITIES")
    private String amenities;
}

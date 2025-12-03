package com.caddospace.caddoaccommodationhub.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Entity
@Table(name = "HOSTEL_BED")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class HostelBed extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BED_ID")
    private long bedId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID", nullable = false)
    private Room room;

    @Column(name = "BED_NUMBER", nullable = false)
    private String bedNumber;

    @Column(name = "IS_AVAILABLE")
    private Boolean available = true;

}

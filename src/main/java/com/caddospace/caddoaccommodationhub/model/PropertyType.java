package com.caddospace.caddoaccommodationhub.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Entity
@Table(name = "PROPERTY_TYPE")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class PropertyType extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TYPE_ID")
    private long typeId;

    @Column(name = "TYPE_NAME", nullable = false)
    private String typeName;
}

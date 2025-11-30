package com.caddospace.caddoaccommodationhub.dto;

import lombok.Data;

@Data
public class PropertyDTO {

    private long propertyId;

    private String name;

    private Long typeId;

    private String description;

    private String address;

    private String city;

    private String state;

    private String country;

    private String zipCode;

    private Long userId;
}

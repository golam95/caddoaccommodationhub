package com.caddospace.caddoaccommodationhub.dto;

import lombok.Data;

@Data
public class VacationRentalDetailsDTO {

    private long rentalId;

    private long propertyId;

    private int minStayDays;

    private int maxGuests;

    private double cleaningFee;
}

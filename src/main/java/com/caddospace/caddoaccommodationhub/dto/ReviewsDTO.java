package com.caddospace.caddoaccommodationhub.dto;

import lombok.Data;

@Data
public class ReviewsDTO {

    private long reviewId;

    private long propertyId;

    private long userId;

    private int rating;

    private String comment;
}

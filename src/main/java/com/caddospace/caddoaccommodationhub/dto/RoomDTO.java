package com.caddospace.caddoaccommodationhub.dto;

import com.caddospace.caddoaccommodationhub.Enum.RoomTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoomDTO extends AuditableDTO {

    private long roomId;

    private long propertyId;

    private String roomNumber;

    private RoomTypeEnum type;

    private int capacity;

    private double pricePerNight;

    private String description;

    private String amenities;
}

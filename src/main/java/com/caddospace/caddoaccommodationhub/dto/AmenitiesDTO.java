package com.caddospace.caddoaccommodationhub.dto;

import com.caddospace.caddoaccommodationhub.model.Amenities;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AmenitiesDTO extends AuditableDTO {

    private long amenitiesId;

    private String name;

    private String description;
}

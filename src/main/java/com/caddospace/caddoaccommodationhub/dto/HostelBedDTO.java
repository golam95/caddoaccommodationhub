package com.caddospace.caddoaccommodationhub.dto;

import lombok.Data;

@Data
public class HostelBedDTO {

    private long bedId;

    private long roomId;

    private String bedNumber;

    private Boolean available;
}

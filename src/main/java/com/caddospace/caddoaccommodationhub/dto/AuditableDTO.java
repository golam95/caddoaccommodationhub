package com.caddospace.caddoaccommodationhub.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuditableDTO {

    private String createdBy;

    private LocalDateTime createdDate;

    private String modifiedBy;

    private LocalDateTime modifiedDate;
}

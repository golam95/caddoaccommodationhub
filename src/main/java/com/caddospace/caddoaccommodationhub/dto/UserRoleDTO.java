package com.caddospace.caddoaccommodationhub.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserRoleDTO extends AuditableDTO {

    private long roleId;

    private String roleType;
}

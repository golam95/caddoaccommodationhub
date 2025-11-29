package com.caddospace.caddoaccommodationhub.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UsersDTO extends AuditableDTO {

    private long userId;

    private String name;

    private String email;

    private String password;

    private String phone;

    private long roleId;
}

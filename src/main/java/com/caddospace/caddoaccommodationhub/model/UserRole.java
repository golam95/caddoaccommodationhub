package com.caddospace.caddoaccommodationhub.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "USER_ROLE")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class UserRole extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private long roleId;

    @Column(name = "ROLE_TYPE", nullable = false)
    private String roleType;

    // Inverse side of relationship
    @OneToMany(mappedBy = "userRole", fetch = FetchType.LAZY)
    private List<Users> users;
}

package com.caddospace.caddoaccommodationhub.repository;


import com.caddospace.caddoaccommodationhub.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}

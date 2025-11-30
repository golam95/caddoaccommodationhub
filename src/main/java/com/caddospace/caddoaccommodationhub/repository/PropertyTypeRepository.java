package com.caddospace.caddoaccommodationhub.repository;

import com.caddospace.caddoaccommodationhub.model.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyTypeRepository extends JpaRepository<PropertyType, Long> {
}

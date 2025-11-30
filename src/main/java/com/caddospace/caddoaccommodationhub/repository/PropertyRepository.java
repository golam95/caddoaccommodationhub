package com.caddospace.caddoaccommodationhub.repository;

import com.caddospace.caddoaccommodationhub.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}

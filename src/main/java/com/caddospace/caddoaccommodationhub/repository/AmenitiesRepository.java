package com.caddospace.caddoaccommodationhub.repository;

import com.caddospace.caddoaccommodationhub.model.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenitiesRepository extends JpaRepository<Amenities, Long> {
}

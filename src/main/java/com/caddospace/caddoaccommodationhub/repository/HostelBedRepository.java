package com.caddospace.caddoaccommodationhub.repository;

import com.caddospace.caddoaccommodationhub.model.HostelBed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostelBedRepository extends JpaRepository<HostelBed, Long> {
}

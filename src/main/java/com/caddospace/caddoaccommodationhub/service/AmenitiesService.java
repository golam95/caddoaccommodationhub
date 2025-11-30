package com.caddospace.caddoaccommodationhub.service;

import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.AmenitiesDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AmenitiesService {

    ResponseEntity<APIResponse<AmenitiesDTO>> saveAmenities(AmenitiesDTO amenitiesDTO);

    ResponseEntity<APIResponse<AmenitiesDTO>> updateAmenities(Long id, AmenitiesDTO amenitiesDTO);

    ResponseEntity<APIResponse<String>> deleteAmenities(Long id);

    ResponseEntity<APIResponse<List<AmenitiesDTO>>> amenitiesList();
}

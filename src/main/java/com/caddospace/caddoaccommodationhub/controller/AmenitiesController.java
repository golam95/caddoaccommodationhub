package com.caddospace.caddoaccommodationhub.controller;

import com.caddospace.caddoaccommodationhub.constant.APIRoute;
import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.AmenitiesDTO;
import com.caddospace.caddoaccommodationhub.model.Amenities;
import com.caddospace.caddoaccommodationhub.repository.AmenitiesRepository;
import com.caddospace.caddoaccommodationhub.service.AmenitiesService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/amenities")
public class AmenitiesController {
    private final AmenitiesService amenitiesService;

    public AmenitiesController(AmenitiesService amenitiesService) {
        this.amenitiesService = amenitiesService;
    }

    @PostMapping(APIRoute.AMENITIES_SAVE)
    public ResponseEntity<APIResponse<AmenitiesDTO>> saveAmenities(@Valid @RequestBody AmenitiesDTO amenitiesDTO) {
        return amenitiesService.saveAmenities(amenitiesDTO);
    }

    @PutMapping(APIRoute.AMENITIES_UPDATE)
    public ResponseEntity<APIResponse<AmenitiesDTO>> updateAmenities(@PathVariable Long id, @Valid @RequestBody AmenitiesDTO amenitiesDTO) {
        return amenitiesService.updateAmenities(id, amenitiesDTO);
    }

    @DeleteMapping(APIRoute.AMENITIES_DELETE)
    public ResponseEntity<APIResponse<String>> deleteAmenities(@PathVariable Long id) {
        return amenitiesService.deleteAmenities(id);
    }

    @GetMapping(APIRoute.AMENITIES_LIST)
    public ResponseEntity<APIResponse<List<AmenitiesDTO>>> getAmenities() {
        return amenitiesService.amenitiesList();
    }
}

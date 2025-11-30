package com.caddospace.caddoaccommodationhub.serviceImpl;

import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.AmenitiesDTO;
import com.caddospace.caddoaccommodationhub.model.Amenities;
import com.caddospace.caddoaccommodationhub.repository.AmenitiesRepository;
import com.caddospace.caddoaccommodationhub.service.AmenitiesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AmenitiesServiceImpl implements AmenitiesService {
    private final AmenitiesRepository amenitiesRepository;

    public AmenitiesServiceImpl(AmenitiesRepository amenitiesRepository) {
        this.amenitiesRepository = amenitiesRepository;
    }

    private Amenities convertDtoEntity(AmenitiesDTO dto, Amenities entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    private AmenitiesDTO convertDto(Amenities entity) {
        AmenitiesDTO dto = new AmenitiesDTO();
        dto.setAmenitiesId(entity.getAmenitiesId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    @Override
    public ResponseEntity<APIResponse<AmenitiesDTO>> saveAmenities(AmenitiesDTO amenitiesDTO) {

        Amenities amenities = convertDtoEntity(amenitiesDTO, new Amenities());
        Amenities savedAmenities = amenitiesRepository.save(amenities);

        AmenitiesDTO responseDto = convertDto(savedAmenities);
        return new ResponseEntity<>(new APIResponse<>(true, 201, "AMENITIES CREATED SUCCESSFULLY", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<AmenitiesDTO>> updateAmenities(Long id, AmenitiesDTO amenitiesDTO) {
        Amenities existing = amenitiesRepository.findById(id).orElseThrow(() -> new RuntimeException("Amenities Not Found with id: " + id));
        convertDtoEntity(amenitiesDTO, existing);
        Amenities updatedAmenities = amenitiesRepository.save(existing);
        AmenitiesDTO responseDto = convertDto(updatedAmenities);

        return new ResponseEntity<>(new APIResponse<>(true, 200, "UPDATE AMENITIES SUCCESSFULLY", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<String>> deleteAmenities(Long id) {
        if (!amenitiesRepository.existsById(id)) {
            return new ResponseEntity<>(new APIResponse<>(false, 404, "AMENITIES NOT FOUND WITH ID: " + id, null), HttpStatus.NOT_FOUND);
        }

        amenitiesRepository.deleteById(id);
        return new ResponseEntity<>(new APIResponse<>(true, 201, "AMENITIES DELETED SUCCESSFULLY", null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<List<AmenitiesDTO>>> amenitiesList() {

        List<Amenities> amenitiesList = amenitiesRepository.findAll();
        List<AmenitiesDTO> amenitiesDTOList = amenitiesList.stream().map(this::convertDto).toList();

        return new ResponseEntity<>(new APIResponse<>(true, 201, "AMENITIES LIST FETCHED SUCCESSFULLY", amenitiesDTOList), HttpStatus.OK);
    }
}

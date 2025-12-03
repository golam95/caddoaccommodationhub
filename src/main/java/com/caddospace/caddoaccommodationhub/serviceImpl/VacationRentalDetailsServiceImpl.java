package com.caddospace.caddoaccommodationhub.serviceImpl;

import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.VacationRentalDetailsDTO;
import com.caddospace.caddoaccommodationhub.model.Property;
import com.caddospace.caddoaccommodationhub.model.VacationRentalDetails;
import com.caddospace.caddoaccommodationhub.repository.PropertyRepository;
import com.caddospace.caddoaccommodationhub.repository.VacationRentalDetailsRepository;
import com.caddospace.caddoaccommodationhub.service.VacationRentalDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VacationRentalDetailsServiceImpl implements VacationRentalDetailsService {
    private final VacationRentalDetailsRepository vacationRentalDetailsRepository;
    private final PropertyRepository propertyRepository;

    public VacationRentalDetailsServiceImpl(VacationRentalDetailsRepository vacationRentalDetailsRepository, PropertyRepository propertyRepository) {
        this.vacationRentalDetailsRepository = vacationRentalDetailsRepository;
        this.propertyRepository = propertyRepository;
    }

    private VacationRentalDetails convertToEntity(VacationRentalDetailsDTO dto, VacationRentalDetails entity, Property property) {

        entity.setProperty(property);
        entity.setMinStayDays(dto.getMinStayDays());
        entity.setMaxGuests(dto.getMaxGuests());
        entity.setCleaningFee(dto.getCleaningFee());
        return entity;
    }

    private VacationRentalDetailsDTO convertToDTO(VacationRentalDetails entity) {

        VacationRentalDetailsDTO dto = new VacationRentalDetailsDTO();
        dto.setRentalId(entity.getRentalId());
        dto.setPropertyId(entity.getProperty().getPropertyId());
        dto.setMinStayDays(entity.getMinStayDays());
        dto.setMaxGuests(entity.getMaxGuests());
        dto.setCleaningFee(entity.getCleaningFee());
        return dto;

    }

    @Override
    public ResponseEntity<APIResponse<VacationRentalDetailsDTO>> saveVacationRentalDetails(VacationRentalDetailsDTO dto) {
        Property existing = propertyRepository.findById(dto.getPropertyId()).orElseThrow(() -> new RuntimeException("Property not found with id " + dto.getPropertyId()));

        VacationRentalDetails entity = convertToEntity(dto, new VacationRentalDetails(), existing);
        VacationRentalDetails savedEntity = vacationRentalDetailsRepository.save(entity);
        VacationRentalDetailsDTO responseDto = convertToDTO(savedEntity);

        return new ResponseEntity<>(new APIResponse<>(true, 201, "Vacation Rental created ", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<VacationRentalDetailsDTO>> updateVacationRentalDetails(Long id, VacationRentalDetailsDTO dto) {

        VacationRentalDetails existing = vacationRentalDetailsRepository.findById(id).orElseThrow(() -> new RuntimeException("Property not found with id " + id));

        convertToEntity(dto, existing, existing.getProperty());
        VacationRentalDetails updatedEntity = vacationRentalDetailsRepository.save(existing);
        VacationRentalDetailsDTO responseDto = convertToDTO(updatedEntity);

        return new ResponseEntity<>(new APIResponse<>(true, 200, "Vacation Rental updated ", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<String>> deleteVacationRentalDetails(Long id) {
        if (vacationRentalDetailsRepository.existsById(id)) {
            vacationRentalDetailsRepository.deleteById(id);
            return new ResponseEntity<>(new APIResponse<>(true, 201, "Vacation Rental Deleted successfully", null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new APIResponse<>(false, 404, "Vacation Rental not found with id: " + id, null), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<APIResponse<List<VacationRentalDetailsDTO>>> ListVacationRentalDetails() {

        List<VacationRentalDetails> list = vacationRentalDetailsRepository.findAll();
        List<VacationRentalDetailsDTO> responseList = list.stream().map(this::convertToDTO).toList();

        return new ResponseEntity<>(new APIResponse<>(true, 200, "Vacation Rental list fetched successfully", responseList), HttpStatus.OK);
    }
}

package com.caddospace.caddoaccommodationhub.serviceImpl;

import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.PropertyTypeDTO;
import com.caddospace.caddoaccommodationhub.model.PropertyType;
import com.caddospace.caddoaccommodationhub.repository.PropertyTypeRepository;
import com.caddospace.caddoaccommodationhub.service.PropertyTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyTypeServiceImpl implements PropertyTypeService {

    private final PropertyTypeRepository propertyTypeRepository;

    public PropertyTypeServiceImpl(PropertyTypeRepository propertyTypeRepository) {
        this.propertyTypeRepository = propertyTypeRepository;
    }

    private PropertyType convertPropertyTypeDTOtoPropertyType(PropertyTypeDTO propertyTypeDTO, PropertyType entity) {

        entity.setTypeName(propertyTypeDTO.getTypeName());
        return entity;
    }

    private PropertyTypeDTO convertToDTO(PropertyType entity) {
        PropertyTypeDTO propertyTypeDTO = new PropertyTypeDTO();
        propertyTypeDTO.setPropertyId(entity.getTypeId());
        propertyTypeDTO.setTypeName(entity.getTypeName());
        return propertyTypeDTO;
    }

    @Override
    public ResponseEntity<APIResponse<PropertyTypeDTO>> savePropertyType(PropertyTypeDTO dto) {

        PropertyType propertyType = convertPropertyTypeDTOtoPropertyType(dto, new PropertyType());
        PropertyType saved = propertyTypeRepository.save(propertyType);
        PropertyTypeDTO responseDto = convertToDTO(saved);
        return new ResponseEntity<>(new APIResponse<>(true, 201, "PROPERTY TYPE SAVE SUCCESSFULLY", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<PropertyTypeDTO>> updatePropertyType(Long id, PropertyTypeDTO dto) {

        PropertyType existing = propertyTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("PropertyType not found with id: " + id));
        convertPropertyTypeDTOtoPropertyType(dto, existing);
        PropertyType updated = propertyTypeRepository.save(existing);
        PropertyTypeDTO responseDto = convertToDTO(updated);
        return new ResponseEntity<>(new APIResponse<>(true, 200, "PROPERTY TYPE UPDATE SUCCESSFULLY", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<String>> deletePropertyType(Long id) {
        if (propertyTypeRepository.existsById(id)) {
            propertyTypeRepository.deleteById(id);
            return new ResponseEntity<>(new APIResponse<>(true, 201, "PROPERTY TYPE DELETED SUCCESSFULLY", null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new APIResponse<>(true, 404, "PROPERTY TYPE NOT FOUND", null), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<APIResponse<List<PropertyTypeDTO>>> propertyTypeList() {
        List<PropertyType> propertyTypes = propertyTypeRepository.findAll();
        List<PropertyTypeDTO> responseDto = propertyTypes.stream().map(this::convertToDTO).toList();
        return new ResponseEntity<>(new APIResponse<>(true, 200, "PROPERTY TYPE LIST FETCHED SUCCESSFULLY", responseDto), HttpStatus.OK);
    }
}

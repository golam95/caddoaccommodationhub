package com.caddospace.caddoaccommodationhub.serviceImpl;

import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.PropertyDTO;
import com.caddospace.caddoaccommodationhub.model.Property;
import com.caddospace.caddoaccommodationhub.model.PropertyType;
import com.caddospace.caddoaccommodationhub.model.Users;
import com.caddospace.caddoaccommodationhub.repository.PropertyRepository;
import com.caddospace.caddoaccommodationhub.repository.PropertyTypeRepository;
import com.caddospace.caddoaccommodationhub.repository.UsersRepository;
import com.caddospace.caddoaccommodationhub.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;
    private final PropertyTypeRepository propertyTypeRepository;
    private final UsersRepository usersRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository, PropertyTypeRepository propertyTypeRepository, UsersRepository usersRepository) {
        this.propertyRepository = propertyRepository;
        this.propertyTypeRepository = propertyTypeRepository;
        this.usersRepository = usersRepository;
    }

    private Property convertDTOToEntity(PropertyDTO dto, Property entity, PropertyType propertyType, Users user) {
        entity.setName(dto.getName());
        entity.setType(propertyType);
        entity.setDescription(dto.getDescription());
        entity.setAddress(dto.getAddress());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setCountry(dto.getCountry());
        entity.setZipCode(dto.getZipCode());
        entity.setUsers(user);
        return entity;
    }

    private PropertyDTO convertEntityToDTO(Property entity) {
        PropertyDTO dto = new PropertyDTO();
        dto.setPropertyId(entity.getPropertyId());
        dto.setName(entity.getName());
        dto.setTypeId(entity.getType().getTypeId());
        dto.setDescription(entity.getDescription());
        dto.setAddress(entity.getAddress());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        dto.setCountry(entity.getCountry());
        dto.setZipCode(entity.getZipCode());
        dto.setUserId(entity.getUsers().getUserId());
        return dto;
    }

    @Override
    public ResponseEntity<APIResponse<PropertyDTO>> saveProperty(PropertyDTO dto) {
        PropertyType existingType = propertyTypeRepository.findById(dto.getTypeId()).orElseThrow(() -> new RuntimeException("Type not found with id " + dto.getTypeId()));
        Users existingUser = usersRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found with id " + dto.getUserId()));

        Property property = convertDTOToEntity(dto, new Property(), existingType, existingUser);
        Property saved = propertyRepository.save(property);

        PropertyDTO responseDto = convertEntityToDTO(saved);
        return new ResponseEntity<>(new APIResponse<>(true, 201, "PROPERTY CREATED SUCCESSFULLY", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<PropertyDTO>> updateProperty(Long id, PropertyDTO dto) {

        Property existing = propertyRepository.findById(id).orElseThrow(() -> new RuntimeException("Property not found with id " + id));
        PropertyType existingType = propertyTypeRepository.findById(dto.getTypeId()).orElseThrow(() -> new RuntimeException("Type not found with id " + dto.getTypeId()));
        Users existingUser = usersRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found with id " + dto.getUserId()));

        convertDTOToEntity(dto, existing, existingType, existingUser);
        Property updated = propertyRepository.save(existing);
        PropertyDTO responseDto = convertEntityToDTO(updated);

        return new ResponseEntity<>(new APIResponse<>(true, 200, "PROPERTY UPDATED SUCCESSFULLY", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<String>> deleteProperty(Long id) {

        if (propertyRepository.existsById(id)) {
            propertyRepository.deleteById(id);
            return new ResponseEntity<>(new APIResponse<>(true, 201, "PROPERTY DELETED SUCCESSFULLY", null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new APIResponse<>(false, 400, "PROPERTY NOT FOUND WITH ID: " + id, null), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<APIResponse<List<PropertyDTO>>> propertyList() {

        List<Property> properties = propertyRepository.findAll();
        List<PropertyDTO> dtoList = properties.stream().map(this::convertEntityToDTO).toList();
        return new ResponseEntity<>(new APIResponse<>(true, 200, "PROPERTIES FETCHED SUCCESSFULLY", dtoList), HttpStatus.OK);
    }
}

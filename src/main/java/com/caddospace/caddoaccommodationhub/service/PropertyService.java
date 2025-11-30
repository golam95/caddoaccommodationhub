package com.caddospace.caddoaccommodationhub.service;

import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.PropertyDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PropertyService {

    ResponseEntity<APIResponse<PropertyDTO>> saveProperty(PropertyDTO propertyDTO);

    ResponseEntity<APIResponse<PropertyDTO>> updateProperty(Long id, PropertyDTO propertyDTO);

    ResponseEntity<APIResponse<String>> deleteProperty(Long id);

    ResponseEntity<APIResponse<List<PropertyDTO>>> propertyList();
}

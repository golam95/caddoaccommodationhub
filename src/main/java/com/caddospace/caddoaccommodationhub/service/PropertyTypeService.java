package com.caddospace.caddoaccommodationhub.service;

import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.PropertyTypeDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PropertyTypeService {

    ResponseEntity<APIResponse<PropertyTypeDTO>> savePropertyType(PropertyTypeDTO dto);

    ResponseEntity<APIResponse<PropertyTypeDTO>> updatePropertyType(Long id, PropertyTypeDTO dto);

    ResponseEntity<APIResponse<String>> deletePropertyType(Long id);

    ResponseEntity<APIResponse<List<PropertyTypeDTO>>> propertyTypeList();
}

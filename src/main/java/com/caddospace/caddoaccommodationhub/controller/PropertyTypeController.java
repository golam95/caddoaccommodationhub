package com.caddospace.caddoaccommodationhub.controller;

import com.caddospace.caddoaccommodationhub.constant.APIRoute;
import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.PropertyTypeDTO;
import com.caddospace.caddoaccommodationhub.service.PropertyTypeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/property-type")
public class PropertyTypeController {

    private final PropertyTypeService propertyTypeService;

    public PropertyTypeController(PropertyTypeService propertyTypeService) {
        this.propertyTypeService = propertyTypeService;
    }

    @PostMapping(APIRoute.PROPERTY_TYPE_SAVE)
    public ResponseEntity<APIResponse<PropertyTypeDTO>> savePropertyType(@Valid @RequestBody PropertyTypeDTO propertyTypeDTO) {
        return propertyTypeService.savePropertyType(propertyTypeDTO);
    }

    @PutMapping(APIRoute.PROPERTY_TYPE_UPDATE)
    public ResponseEntity<APIResponse<PropertyTypeDTO>> updatePropertyType(@PathVariable Long id, @Valid @RequestBody PropertyTypeDTO propertyTypeDTO) {
        return propertyTypeService.updatePropertyType(id, propertyTypeDTO);
    }

    @DeleteMapping(APIRoute.PROPERTY_TYPE_DELETE)
    public ResponseEntity<APIResponse<String>> deletePropertyType(@PathVariable Long id) {
        return propertyTypeService.deletePropertyType(id);
    }

    @GetMapping(APIRoute.PROPERTY_TYPE_LIST)
    public ResponseEntity<APIResponse<List<PropertyTypeDTO>>> getPropertyTypes() {
        return propertyTypeService.propertyTypeList();
    }
}

package com.caddospace.caddoaccommodationhub.controller;

import com.caddospace.caddoaccommodationhub.constant.APIRoute;
import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.PropertyDTO;

import com.caddospace.caddoaccommodationhub.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping(APIRoute.PROPERTY_SAVE)
    public ResponseEntity<APIResponse<PropertyDTO>> saveProperty(@Valid @RequestBody PropertyDTO dto) {
        return propertyService.saveProperty(dto);
    }

    @PutMapping(APIRoute.PROPERTY_UPDATE)
    public ResponseEntity<APIResponse<PropertyDTO>> updateProperty(@PathVariable Long id, @Valid @RequestBody PropertyDTO dto) {
        return propertyService.updateProperty(id, dto);
    }

    @DeleteMapping(APIRoute.PROPERTY_DELETE)
    public ResponseEntity<APIResponse<String>> deleteProperty(@PathVariable Long id) {
        return propertyService.deleteProperty(id);
    }

    @GetMapping(APIRoute.PROPERTY_LIST)
    public ResponseEntity<APIResponse<List<PropertyDTO>>> getAllProperty() {
        return propertyService.propertyList();
    }
}

package com.caddospace.caddoaccommodationhub.controller;

import com.caddospace.caddoaccommodationhub.constant.APIRoute;
import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.HostelBedDTO;
import com.caddospace.caddoaccommodationhub.service.HostelBedService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hostel-beds")
public class HostelBedController {

    private final HostelBedService hostelBedService;

    public HostelBedController(HostelBedService hostelBedService) {
        this.hostelBedService = hostelBedService;
    }

    @PostMapping(APIRoute.HOSTEL_BED_SAVE)
    public ResponseEntity<APIResponse<HostelBedDTO>> saveHostelBed(@Valid @RequestBody HostelBedDTO dto) {
        return hostelBedService.saveHostelBed(dto);
    }

    @PutMapping(APIRoute.HOSTEL_BED_UPDATE)
    public ResponseEntity<APIResponse<HostelBedDTO>> updateHostelBed(@PathVariable Long id, @Valid @RequestBody HostelBedDTO dto) {
        return hostelBedService.updateHostelBed(id, dto);
    }

    @DeleteMapping(APIRoute.HOSTEL_BED_DELETE)
    public ResponseEntity<APIResponse<String>> deleteHostelBed(@PathVariable Long id) {
        return hostelBedService.deleteHostelBed(id);
    }

    @GetMapping(APIRoute.HOSTEL_BED_LIST)
    public ResponseEntity<APIResponse<List<HostelBedDTO>>> bedLists() {
        return hostelBedService.bedsList();
    }
}

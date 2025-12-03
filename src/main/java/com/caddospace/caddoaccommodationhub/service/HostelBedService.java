package com.caddospace.caddoaccommodationhub.service;

import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.HostelBedDTO;
import com.caddospace.caddoaccommodationhub.model.HostelBed;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HostelBedService {

    ResponseEntity<APIResponse<HostelBedDTO>> saveHostelBed(HostelBedDTO dto);

    ResponseEntity<APIResponse<HostelBedDTO>> updateHostelBed(Long id, HostelBedDTO dto);

    ResponseEntity<APIResponse<String>> deleteHostelBed(Long id);

    ResponseEntity<APIResponse<List<HostelBedDTO>>> bedsList();
}

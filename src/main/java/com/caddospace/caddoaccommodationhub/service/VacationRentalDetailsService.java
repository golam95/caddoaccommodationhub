package com.caddospace.caddoaccommodationhub.service;

import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.VacationRentalDetailsDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VacationRentalDetailsService {

    ResponseEntity<APIResponse<VacationRentalDetailsDTO>> saveVacationRentalDetails(VacationRentalDetailsDTO dto);

    ResponseEntity<APIResponse<VacationRentalDetailsDTO>> updateVacationRentalDetails(Long id, VacationRentalDetailsDTO dto);

    ResponseEntity<APIResponse<String>> deleteVacationRentalDetails(Long id);

    ResponseEntity<APIResponse<List<VacationRentalDetailsDTO>>> ListVacationRentalDetails();
}

package com.caddospace.caddoaccommodationhub.controller;

import com.caddospace.caddoaccommodationhub.constant.APIRoute;
import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.VacationRentalDetailsDTO;
import com.caddospace.caddoaccommodationhub.service.VacationRentalDetailsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vacation-rental")
public class VacationRentalDetailsController {

    private final VacationRentalDetailsService vacationRentalDetailsService;

    public VacationRentalDetailsController(VacationRentalDetailsService vacationRentalDetailsService) {
        this.vacationRentalDetailsService = vacationRentalDetailsService;
    }

    @PostMapping(APIRoute.VACATION_RENTAL_SAVE)
    public ResponseEntity<APIResponse<VacationRentalDetailsDTO>> save(@Valid @RequestBody VacationRentalDetailsDTO dto) {
        return vacationRentalDetailsService.saveVacationRentalDetails(dto);
    }

    @PutMapping(APIRoute.VACATION_RENTAL_UPDATE)
    public ResponseEntity<APIResponse<VacationRentalDetailsDTO>> update(@PathVariable Long id, @Valid @RequestBody VacationRentalDetailsDTO dto) {
        return vacationRentalDetailsService.updateVacationRentalDetails(id, dto);
    }

    @DeleteMapping(APIRoute.VACATION_RENTAL_DELETE)
    public ResponseEntity<APIResponse<String>> delete(@PathVariable Long id) {
        return vacationRentalDetailsService.deleteVacationRentalDetails(id);
    }

    @GetMapping(APIRoute.VACATION_RENTAL_LIST)
    public ResponseEntity<APIResponse<List<VacationRentalDetailsDTO>>> listVacationRental() {
        return vacationRentalDetailsService.ListVacationRentalDetails();
    }
}

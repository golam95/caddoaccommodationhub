package com.caddospace.caddoaccommodationhub.controller;

import com.caddospace.caddoaccommodationhub.constant.APIRoute;
import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.BookingDTO;
import com.caddospace.caddoaccommodationhub.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping(APIRoute.BOOKING_SAVE)
    public ResponseEntity<APIResponse<BookingDTO>> saveBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        return bookingService.saveBooking(bookingDTO);
    }

    @PutMapping(APIRoute.BOOKING_UPDATE)
    public ResponseEntity<APIResponse<BookingDTO>> updateBooking(@PathVariable Long id, @Valid @RequestBody BookingDTO bookingDTO) {
        return bookingService.updateBooking(id, bookingDTO);
    }

    @DeleteMapping(APIRoute.BOOKING_DELETE)
    public ResponseEntity<APIResponse<String>> deleteBooking(@PathVariable Long id) {
        return bookingService.deleteBooking(id);
    }

    @GetMapping(APIRoute.BOOKING_LIST)
    public ResponseEntity<APIResponse<List<BookingDTO>>> bookingList() {
        return bookingService.bookingList();
    }
}

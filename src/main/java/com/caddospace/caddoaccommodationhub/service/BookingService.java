package com.caddospace.caddoaccommodationhub.service;

import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.BookingDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {

    ResponseEntity<APIResponse<BookingDTO>> saveBooking(BookingDTO dto);

    ResponseEntity<APIResponse<BookingDTO>> updateBooking(Long id, BookingDTO dto);

    ResponseEntity<APIResponse<String>> deleteBooking(Long id);

    ResponseEntity<APIResponse<List<BookingDTO>>> bookingList();
}

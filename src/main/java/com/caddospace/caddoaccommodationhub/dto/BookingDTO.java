package com.caddospace.caddoaccommodationhub.dto;

import com.caddospace.caddoaccommodationhub.Enum.BookingStatus;
import lombok.Data;


import java.time.LocalDate;

@Data
public class BookingDTO {

    private Long bookingId;

    private Long userId;

    private Long propertyId;

    private Long roomId;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private double totalPrice;

    private BookingStatus status;
}

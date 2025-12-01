package com.caddospace.caddoaccommodationhub.dto;

import com.caddospace.caddoaccommodationhub.Enum.PaymentMethod;
import com.caddospace.caddoaccommodationhub.Enum.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDTO {

    private long paymentId;

    private long bookingId;

    private double amount;

    private LocalDateTime paymentDate;

    private PaymentMethod paymentMethod;

    private PaymentStatus status;

    private String transactionId;
}

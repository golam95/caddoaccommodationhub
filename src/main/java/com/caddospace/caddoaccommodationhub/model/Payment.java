package com.caddospace.caddoaccommodationhub.model;

import com.caddospace.caddoaccommodationhub.Enum.PaymentMethod;
import com.caddospace.caddoaccommodationhub.Enum.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PAYMENT")
@EqualsAndHashCode(callSuper = false)
public class Payment extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAYMENT_ID")
    private long paymentId;

    // ----------- RELATION -----------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKING_ID", nullable = false)
    private Booking booking;

    // ----------- FIELDS -----------
    @Column(name = "AMOUNT", nullable = false)
    private double amount;

    @Column(name = "PAYMENT_DATE", nullable = false)
    private LocalDateTime paymentDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_METHOD", nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private PaymentStatus status = PaymentStatus.PENDING;

    @Column(name = "TRANSACTION_ID")
    private String transactionId;
}

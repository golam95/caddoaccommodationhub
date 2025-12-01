package com.caddospace.caddoaccommodationhub.serviceImpl;

import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.PaymentDTO;
import com.caddospace.caddoaccommodationhub.model.Booking;
import com.caddospace.caddoaccommodationhub.model.Payment;
import com.caddospace.caddoaccommodationhub.repository.BookingRepository;
import com.caddospace.caddoaccommodationhub.repository.PaymentRepository;
import com.caddospace.caddoaccommodationhub.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    private Payment convertPaymentDTOtoEntity(PaymentDTO dto, Payment entity, Booking booking) {
        entity.setBooking(booking);
        entity.setAmount(dto.getAmount());
        entity.setPaymentDate(dto.getPaymentDate());
        entity.setPaymentMethod(dto.getPaymentMethod());
        entity.setStatus(dto.getStatus());
        entity.setTransactionId(dto.getTransactionId());
        return entity;
    }

    private PaymentDTO convertEntityToPaymentDTO(Payment entity) {
        PaymentDTO dto = new PaymentDTO();
        dto.setPaymentId(entity.getPaymentId());
        dto.setBookingId(entity.getBooking().getBookingId());
        dto.setAmount(entity.getAmount());
        dto.setPaymentDate(entity.getPaymentDate());
        dto.setPaymentMethod(entity.getPaymentMethod());
        dto.setStatus(entity.getStatus());
        dto.setTransactionId(entity.getTransactionId());
        return dto;
    }

    @Override
    public ResponseEntity<APIResponse<PaymentDTO>> savePayment(PaymentDTO dto) {

        Booking existing = bookingRepository.findById(dto.getBookingId()).orElseThrow(() -> new RuntimeException("Booking not found with id " + dto.getBookingId()));

        Payment payment = convertPaymentDTOtoEntity(dto, new Payment(), existing);
        Payment savedPayment = paymentRepository.save(payment);
        PaymentDTO responseDto = convertEntityToPaymentDTO(savedPayment);

        return new ResponseEntity<>(new APIResponse<>(true, 201, "PAYMENT CREATED", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<PaymentDTO>> updatePayment(Long id, PaymentDTO dto) {
        Payment existing = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found with id " + id));
        convertPaymentDTOtoEntity(dto, existing, existing.getBooking());
        Payment updatedPayment = paymentRepository.save(existing);
        PaymentDTO responseDto = convertEntityToPaymentDTO(updatedPayment);
        return new ResponseEntity<>(new APIResponse<>(true, 200, "PAYMENT UPDATED", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<String>> deletePayment(Long id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
            return new ResponseEntity<>(new APIResponse<>(true, 201, "PAYMENT DELETED", null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new APIResponse<>(false, 404, "PAYMENT NOT FOUND WITH ID:  " + id, null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<List<PaymentDTO>>> paymentList() {
        List<Payment> payments = paymentRepository.findAll();
        List<PaymentDTO> responseDto = payments.stream().map(this::convertEntityToPaymentDTO).toList();
        return new ResponseEntity<>(new APIResponse<>(true, 200, "PAYMENT LIST FETCHED SUCCESSFULLY", responseDto), HttpStatus.OK);
    }
}

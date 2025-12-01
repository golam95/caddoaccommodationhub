package com.caddospace.caddoaccommodationhub.service;

import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.PaymentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PaymentService {

    ResponseEntity<APIResponse<PaymentDTO>> savePayment(PaymentDTO dto);

    ResponseEntity<APIResponse<PaymentDTO>> updatePayment(Long id, PaymentDTO dto);

    ResponseEntity<APIResponse<String>> deletePayment(Long id);

    ResponseEntity<APIResponse<List<PaymentDTO>>> paymentList();
}

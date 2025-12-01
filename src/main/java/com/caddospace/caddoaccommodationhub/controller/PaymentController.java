package com.caddospace.caddoaccommodationhub.controller;

import com.caddospace.caddoaccommodationhub.constant.APIRoute;
import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.PaymentDTO;
import com.caddospace.caddoaccommodationhub.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(APIRoute.PAYMENT_SAVE)
    public ResponseEntity<APIResponse<PaymentDTO>> paymentSave(@Valid @RequestBody PaymentDTO paymentDTO) {
        return paymentService.savePayment(paymentDTO);
    }

    @PutMapping(APIRoute.PAYMENT_UPDATE)
    public ResponseEntity<APIResponse<PaymentDTO>> paymentUpdate(@PathVariable Long id, @Valid @RequestBody PaymentDTO paymentDTO) {
        return paymentService.updatePayment(id, paymentDTO);
    }

    @DeleteMapping(APIRoute.PAYMENT_DELETE)
    public ResponseEntity<APIResponse<String>> paymentDelete(@PathVariable Long id) {
        return paymentService.deletePayment(id);
    }

    @GetMapping(APIRoute.PAYMENT_LIST)
    public ResponseEntity<APIResponse<List<PaymentDTO>>> paymentList() {
        return paymentService.paymentList();
    }


}

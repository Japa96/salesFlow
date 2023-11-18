package com.SalesFlowSA.SalesFlow.controller;

import com.SalesFlowSA.SalesFlow.model.DTO.PaymentMethodDTO;
import com.SalesFlowSA.SalesFlow.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/paymentMethod")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @PostMapping("/registerPaymentMethod")
    public ResponseEntity<Map<String, Object>> registerPaymentMethod(@RequestBody PaymentMethodDTO paymentMethodDTO){
        return paymentMethodService.registerPaymentMethod(paymentMethodDTO);
    }

    @DeleteMapping("/deletePaymentMethod/{id}")
    public ResponseEntity<?> deletePaymentMethod(@PathVariable("id") Long id){
        return paymentMethodService.deletePaymentMethod(id);
    }

}

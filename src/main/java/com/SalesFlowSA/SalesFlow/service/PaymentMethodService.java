package com.SalesFlowSA.SalesFlow.service;

import com.SalesFlowSA.SalesFlow.model.DTO.PaymentMethodDTO;
import com.SalesFlowSA.SalesFlow.model.PaymentMethod;
import com.SalesFlowSA.SalesFlow.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public ResponseEntity<Map<String, Object>> registerPaymentMethod(PaymentMethodDTO paymentMethodDTO){
        try{
            PaymentMethod paymentMethod = new PaymentMethod();
            paymentMethod.setTypeOfPayment(paymentMethodDTO.getTypeOfPayment());

            paymentMethodRepository.save(paymentMethod);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Payment Method registered successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Fail to register Payment Method");
            response.put("error", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}

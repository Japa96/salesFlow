package com.SalesFlowSA.SalesFlow.service;

import com.SalesFlowSA.SalesFlow.model.*;
import com.SalesFlowSA.SalesFlow.model.DTO.*;
import com.SalesFlowSA.SalesFlow.repository.CustomerRepository;
import com.SalesFlowSA.SalesFlow.repository.PaymentMethodRepository;
import com.SalesFlowSA.SalesFlow.repository.SalesPersonRepository;
import com.SalesFlowSA.SalesFlow.repository.SellRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SellService {
    private final SellRepository sellRepository;
    private final CustomerRepository customerRepository;
    private final SalesPersonRepository salesPersonRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public SellService(SellRepository sellRepository, CustomerRepository customerRepository, SalesPersonRepository salesPersonRepository, PaymentMethodRepository paymentMethodRepository) {
        this.sellRepository = sellRepository;
        this.customerRepository = customerRepository;
        this.salesPersonRepository = salesPersonRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public ResponseEntity<Map<String, Object>> registerSell(SellDTO sellDTO){
        try{
            Sell sell = new Sell();

            Customer customer = customerRepository.findById(sellDTO.getCustomer().getId()).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
            SalesPerson salesPerson = salesPersonRepository.findById(sellDTO.getSalesPerson().getId()).orElseThrow(() -> new IllegalArgumentException("Sales Person not found"));
            PaymentMethod paymentMethod = paymentMethodRepository.findById(sellDTO.getPaymentMethod().getId()).orElseThrow(() -> new IllegalArgumentException("PaymentMethod not found"));

            sell.setCustomer(customer);
            sell.setSalesPerson(salesPerson);
            sell.setPaymentMethod(paymentMethod);
            sell.setTotalPrice(sellDTO.getTotalPrice());

            List<SellItem> sellItems = sellDTO.getItems().stream()
                    .map(sellItemDTO -> new SellItem(sellItemDTO.getItem(), sellItemDTO.getQuantity()))
                    .collect(Collectors.toList());

            sell.setItems(sellItems);

            sellRepository.save(sell);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Sell registered successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Fail to register Sell: " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public ResponseEntity<?> searchAllSell(){
        try{
            List<Sell> sellList = new ArrayList<>();
            sellList = sellRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(sellList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail to search all Sells");
        }
    }
}

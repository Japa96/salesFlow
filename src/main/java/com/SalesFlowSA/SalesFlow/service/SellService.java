package com.SalesFlowSA.SalesFlow.service;

import com.SalesFlowSA.SalesFlow.model.*;
import com.SalesFlowSA.SalesFlow.model.DTO.*;
import com.SalesFlowSA.SalesFlow.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SellService {
    private final SellRepository sellRepository;
    private final CustomerRepository customerRepository;
    private final SalesPersonRepository salesPersonRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final ItemRepository itemRepository;

    public SellService(SellRepository sellRepository, CustomerRepository customerRepository,
                       SalesPersonRepository salesPersonRepository,
                       PaymentMethodRepository paymentMethodRepository, ItemRepository itemRepository) {
        this.sellRepository = sellRepository;
        this.customerRepository = customerRepository;
        this.salesPersonRepository = salesPersonRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.itemRepository = itemRepository;
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

            for (int c = 0; c <= sellItems.size(); c++){
                List<Long> ids = sellItems.stream().map(sellItem -> sellItem.getItem().getId()).toList();
                for (int i = 0; i < ids.size(); i++){
                    itemRepository.findById(ids.get(i)).orElseThrow(() -> new IllegalArgumentException("Item not found"));
                }
            }

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

    public ResponseEntity<?> allSellsByCpf(@PathVariable("cpf") String cpf){
        try{
            List<Sell> sellList = new ArrayList<>();
            sellList = sellRepository.findByCpfCustomer(cpf);
            return ResponseEntity.status(HttpStatus.OK).body(sellList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail to search all Sells by CPF");
        }
    }
}

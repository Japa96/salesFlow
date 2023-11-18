package com.SalesFlowSA.SalesFlow.controller;

import com.SalesFlowSA.SalesFlow.model.DTO.CustomerDTO;
import com.SalesFlowSA.SalesFlow.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/registerCustomer")
    public ResponseEntity<Map<String, Object>> registerCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.registerCustomer(customerDTO);
    }

    @GetMapping("/searchAllCustomer")
    public ResponseEntity<?> searchAllCustomer(){
        return customerService.searchAllCustomer();
    }

    @GetMapping("/searchCustomerByCpf/{cpf}")
    public ResponseEntity<?> searchCustomerByCpf(@PathVariable("cpf") String cpf){
        return customerService.searchCustomerByCpf(cpf);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id){
        return customerService.deleteCustomer(id);
    }
}

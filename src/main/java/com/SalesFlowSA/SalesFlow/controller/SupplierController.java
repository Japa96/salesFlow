package com.SalesFlowSA.SalesFlow.controller;

import com.SalesFlowSA.SalesFlow.model.DTO.SupplierDTO;
import com.SalesFlowSA.SalesFlow.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/registerSupplier")
    public ResponseEntity<Map<String, Object>> registerSupplier(@RequestBody SupplierDTO supplierDTO){
        return supplierService.registerSupplier(supplierDTO);
    }
}

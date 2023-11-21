package com.SalesFlowSA.SalesFlow.controller;

import com.SalesFlowSA.SalesFlow.model.DTO.SupplierDTO;
import com.SalesFlowSA.SalesFlow.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/updateSupplier/{id}")
    public ResponseEntity<?> updateSupplier(@PathVariable("id") Long id, @RequestBody SupplierDTO supplierUpdateDTO){
        return supplierService.updateSupplier(id, supplierUpdateDTO);
    }

    @GetMapping("/searchAllSupplier")
    public ResponseEntity<?> searchAllSupplier(){
        return supplierService.searchAllSupplier();
    }
}

package com.SalesFlowSA.SalesFlow.service;

import com.SalesFlowSA.SalesFlow.model.DTO.SupplierDTO;
import com.SalesFlowSA.SalesFlow.model.Supplier;
import com.SalesFlowSA.SalesFlow.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public ResponseEntity<Map<String, Object>> registerSupplier(SupplierDTO supplierDTO){
        try{
            Supplier supplier = new Supplier();
            supplier.setName(supplierDTO.getName());
            supplier.setCountryOfOrigin(supplierDTO.getCountryOfOrigin());

            supplierRepository.save(supplier);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Supplier registered successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Fail to register supplier");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}

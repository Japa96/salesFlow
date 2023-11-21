package com.SalesFlowSA.SalesFlow.service;

import com.SalesFlowSA.SalesFlow.model.DTO.SupplierDTO;
import com.SalesFlowSA.SalesFlow.model.Supplier;
import com.SalesFlowSA.SalesFlow.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public ResponseEntity<?> searchAllSupplier(){
        try{
            List<Supplier> supplierList = new ArrayList<>();
            supplierList = supplierRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(supplierList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail to search all Suppliers");
        }
    }

    public ResponseEntity<?> updateSupplier(@PathVariable("id") Long id, @RequestBody SupplierDTO supplierUpdateDTO){
        try{
            Supplier supplier = supplierRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Supplier id " + id + " not found"));

            updateObject(supplierUpdateDTO, supplier);

            supplierRepository.save(supplier);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Supplier id " + id + " updated successfully");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        }catch (Exception e){
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Fail to update Supplier id " + id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    public static void updateObject(Object source, Object destination) throws IllegalAccessException {
        Class<?> sourceClass = source.getClass();
        Field[] fields = sourceClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            Object value = field.get(source);

            if (value != null) {
                Field destinationField;
                try {
                    destinationField = destination.getClass().getDeclaredField(field.getName());
                } catch (NoSuchFieldException e) {
                    continue;
                }

                destinationField.setAccessible(true);

                if (destinationField.getType().isAssignableFrom(field.getType())) {
                    destinationField.set(destination, value);
                } else {
                    System.out.println("Tipo de dado incompatível para o campo: " + field.getName());
                }
            }
        }
    }
}

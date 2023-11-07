package com.SalesFlowSA.SalesFlow.service;

import com.SalesFlowSA.SalesFlow.model.DTO.SalesPersonDTO;
import com.SalesFlowSA.SalesFlow.model.SalesPerson;
import com.SalesFlowSA.SalesFlow.repository.SalesPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SalesPersonService {

    private final SalesPersonRepository salesPersonRepository;

    @Autowired
    public SalesPersonService(SalesPersonRepository salesPersonRepository) {
        this.salesPersonRepository = salesPersonRepository;
    }

    public ResponseEntity<Map<String, Object>> registerSalesPerson(SalesPersonDTO salesPersonDTO){
        try{
            SalesPerson salesPerson = new SalesPerson();
            salesPerson.setName(salesPersonDTO.getName());
            salesPerson.setLastName(salesPersonDTO.getLastName());
            salesPerson.setCpf(salesPersonDTO.getCpf());

            salesPersonRepository.save(salesPerson);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Sales person registered successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Fail to register Sales person");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}

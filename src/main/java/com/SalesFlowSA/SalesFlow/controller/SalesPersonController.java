package com.SalesFlowSA.SalesFlow.controller;

import com.SalesFlowSA.SalesFlow.model.DTO.SalesPersonDTO;
import com.SalesFlowSA.SalesFlow.service.SalesPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/salesPerson")
public class SalesPersonController {

    private final SalesPersonService salesPersonService;

    @Autowired
    public SalesPersonController(SalesPersonService salesPersonService) {
        this.salesPersonService = salesPersonService;
    }

    @PostMapping("/registerSalesPerson")
    public ResponseEntity<Map<String, Object>> registerSalesPerson(@RequestBody SalesPersonDTO salesPersonDTO){
        return salesPersonService.registerSalesPerson(salesPersonDTO);
    }
}

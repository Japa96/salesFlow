package com.SalesFlowSA.SalesFlow.controller;

import com.SalesFlowSA.SalesFlow.model.DTO.SellDTO;
import com.SalesFlowSA.SalesFlow.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/sell")
public class SellController {

    private final SellService sellService;

    @Autowired
    public SellController(SellService sellService) {
        this.sellService = sellService;
    }

    @PostMapping("/registerSell")
    public ResponseEntity<Map<String, Object>> registerSell(@RequestBody SellDTO sellDTO){
        return sellService.registerSell(sellDTO);
    }

    @GetMapping("/searchAllSells")
    public ResponseEntity<?> searchAllSelles(){
        return sellService.searchAllSell();
    }
}

package com.SalesFlowSA.SalesFlow.controller;

import com.SalesFlowSA.SalesFlow.model.DTO.ItemDTO;
import com.SalesFlowSA.SalesFlow.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/item")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/registerItem")
    public ResponseEntity<Map<String, Object>> registerItem(@RequestBody ItemDTO itemDTO){
        return itemService.registerItem(itemDTO);
    }

    @DeleteMapping("/deleteItem/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable("id") Long id){
        return itemService.deleteItem(id);
    }
}

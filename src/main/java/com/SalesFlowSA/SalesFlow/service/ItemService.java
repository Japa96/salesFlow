package com.SalesFlowSA.SalesFlow.service;

import com.SalesFlowSA.SalesFlow.model.Item;
import com.SalesFlowSA.SalesFlow.model.ItemCategory;
import com.SalesFlowSA.SalesFlow.model.DTO.ItemDTO;
import com.SalesFlowSA.SalesFlow.model.Supplier;
import com.SalesFlowSA.SalesFlow.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ResponseEntity<Map<String, Object>> registerItem(ItemDTO itemDTO){
        try{
            Item item = new Item();
            item.setName(itemDTO.getName());
            item.setDescription(itemDTO.getDescription());
            item.setPrice(itemDTO.getPrice());
            item.setQuantity(itemDTO.getQuantity());
            item.setUnitOfMeasurement(itemDTO.getUnitOfMeasurement());

            ItemCategory itemCategory = new ItemCategory();
            itemCategory.setName(itemDTO.getItemCategory().getName());

            Supplier supplier = new Supplier();
            supplier.setName(itemDTO.getSupplier().getName());
            supplier.setCountryOfOrigin(itemDTO.getSupplier().getCountryOfOrigin());

            item.setItemCategory(itemCategory);
            item.setSupplier(supplier);
            itemRepository.save(item);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Item registered successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){

            Map<String, Object> response = new HashMap<>();
            response.put("message","Fail to register Item");
            response.put("error", e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}

package com.SalesFlowSA.SalesFlow.service;

import com.SalesFlowSA.SalesFlow.model.Item;
import com.SalesFlowSA.SalesFlow.model.ItemCategory;
import com.SalesFlowSA.SalesFlow.model.DTO.ItemDTO;
import com.SalesFlowSA.SalesFlow.model.Supplier;
import com.SalesFlowSA.SalesFlow.repository.ItemRepository;
import com.SalesFlowSA.SalesFlow.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, SupplierRepository supplierRepository) {
        this.itemRepository = itemRepository;
        this.supplierRepository = supplierRepository;
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

            Supplier supplier = createOrUpdateSupplier(itemDTO);

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

    public ResponseEntity<?> deleteItem(@PathVariable("id") Long id){
        try{
            itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Item not found"));
            itemRepository.deleteById(id);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Item " + id + " deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            Map<String, Object> response = new HashMap<>();
            response.put("message","Fail to delete Item " + id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    private Supplier createOrUpdateSupplier(ItemDTO itemDTO) {
        Supplier supplier = new Supplier();

        if (Objects.isNull(itemDTO.getSupplier().getId()) && Objects.nonNull(itemDTO.getSupplier().getName())) {
            supplier.setName(itemDTO.getSupplier().getName());
            supplier.setCountryOfOrigin(itemDTO.getSupplier().getCountryOfOrigin());
        } else if (Objects.isNull(itemDTO.getSupplier().getName()) && Objects.nonNull(itemDTO.getSupplier().getId())) {
            supplier = supplierRepository.findById(itemDTO.getSupplier().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Supplier ID not found"));
        } else {
            throw new IllegalArgumentException("Invalid supplier");
        }

        return supplier;
    }
}

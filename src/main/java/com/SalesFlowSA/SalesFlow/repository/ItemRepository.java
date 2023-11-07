package com.SalesFlowSA.SalesFlow.repository;

import com.SalesFlowSA.SalesFlow.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Object> {
}

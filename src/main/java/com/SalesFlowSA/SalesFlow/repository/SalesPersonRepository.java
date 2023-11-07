package com.SalesFlowSA.SalesFlow.repository;

import com.SalesFlowSA.SalesFlow.model.SalesPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesPersonRepository extends JpaRepository<SalesPerson, Long> {
}

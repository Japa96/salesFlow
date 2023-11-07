package com.SalesFlowSA.SalesFlow.repository;

import com.SalesFlowSA.SalesFlow.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

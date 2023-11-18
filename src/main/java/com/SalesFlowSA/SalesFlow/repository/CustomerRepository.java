package com.SalesFlowSA.SalesFlow.repository;

import com.SalesFlowSA.SalesFlow.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "SELECT * FROM salesflow.customer where cpf = :cpf", nativeQuery = true)
    List<Customer> findCustomerByCpf(@Param("cpf") String cpf);
}

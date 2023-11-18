package com.SalesFlowSA.SalesFlow.repository;

import com.SalesFlowSA.SalesFlow.model.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SellRepository extends JpaRepository<Sell, Long> {
    @Query(value = "select salesflow.sell.id, salesflow.sell.total_price, salesflow.sell.customer_id, salesflow.sell.payment_method_id, salesflow.sell.sales_person_id " +
            "from salesflow.sell inner join salesflow.customer on salesflow.customer.id = salesflow.sell.customer_id where salesflow.customer.cpf = :cpf", nativeQuery = true)
    List<Sell> findByCpfCustomer(@Param("cpf") String cpf);
}

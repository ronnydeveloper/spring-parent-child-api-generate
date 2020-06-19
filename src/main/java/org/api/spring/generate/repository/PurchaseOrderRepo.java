package org.api.spring.generate.repository;

import org.api.spring.generate.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder, Long> {

    @Query(value = "SELECT (max(id) + 1) as max FROM PurchaseOrder")
    long max();

} 
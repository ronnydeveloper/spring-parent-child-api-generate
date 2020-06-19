package org.api.spring.generate.repository;

import org.api.spring.generate.entity.PurchaseOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderLineRepo extends JpaRepository<PurchaseOrderLine, Long> {

    @Query(value = "SELECT (max(lineId) + 1) as max FROM PurchaseOrderLine")
    long max();

} 
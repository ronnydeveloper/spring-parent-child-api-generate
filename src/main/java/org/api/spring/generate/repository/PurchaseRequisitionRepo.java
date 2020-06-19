package org.api.spring.generate.repository;

import org.api.spring.generate.entity.PurchaseRequisition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRequisitionRepo extends JpaRepository<PurchaseRequisition, Long> {

    @Query(value = "SELECT (max(id) + 1) as max FROM PurchaseRequisition")
    long max();

} 
package org.api.spring.generate.repository;

import org.api.spring.generate.entity.PurchaseOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderDetailRepo extends JpaRepository<PurchaseOrderDetail, Long> {

    @Query(value = "SELECT (max(poDetailId) + 1) as max FROM PurchaseOrderDetail")
    long max();

} 
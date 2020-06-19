package core.api.inherit.springparentchildapigenerate.repository;

import core.api.inherit.springparentchildapigenerate.entity.PurchaseRequisitionLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRequisitionLineRepo extends JpaRepository<PurchaseRequisitionLine, Long> {

    @Query(value = "SELECT (max(lineId) + 1) as max FROM PurchaseRequisitionLine")
    long max();

} 
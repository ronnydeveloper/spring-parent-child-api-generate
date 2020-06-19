package core.api.inherit.springparentchildapigenerate.repository;

import core.api.inherit.springparentchildapigenerate.entity.PurchaseContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseContractRepo extends JpaRepository<PurchaseContract, Long> {

    @Query(value = "SELECT (max(id) + 1) as max FROM PurchaseContract")
    long max();

} 
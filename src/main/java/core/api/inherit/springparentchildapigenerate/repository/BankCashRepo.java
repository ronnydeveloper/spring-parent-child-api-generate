package core.api.inherit.springparentchildapigenerate.repository;

import core.api.inherit.springparentchildapigenerate.entity.BankCash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BankCashRepo extends JpaRepository<BankCash, Long> {

    @Query(value = "SELECT (max(id) + 1) as max FROM BankCash")
    long max();

} 
package org.api.spring.generate.repository;

import org.api.spring.generate.entity.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRateRepo extends JpaRepository<CurrencyRate, String> {

    @Query(value = "SELECT (max(currency) + 1) as max FROM CurrencyRate")
    long max();

} 
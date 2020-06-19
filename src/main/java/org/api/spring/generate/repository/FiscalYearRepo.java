package org.api.spring.generate.repository;

import org.api.spring.generate.entity.FiscalYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FiscalYearRepo extends JpaRepository<FiscalYear, Long> {

    @Query(value = "SELECT (max(id) + 1) as max FROM FiscalYear")
    long max();

} 
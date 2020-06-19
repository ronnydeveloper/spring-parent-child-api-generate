package org.api.spring.generate.repository;

import org.api.spring.generate.entity.Fiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FiscalRepo extends JpaRepository<Fiscal, Long> {

    @Query(value = "SELECT (max(id) + 1) as max FROM Fiscal")
    long max();

} 
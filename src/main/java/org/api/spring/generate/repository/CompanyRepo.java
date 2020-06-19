package org.api.spring.generate.repository;

import org.api.spring.generate.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {

    @Query(value = "SELECT (max(id) + 1) as max FROM Company")
    long max();

} 
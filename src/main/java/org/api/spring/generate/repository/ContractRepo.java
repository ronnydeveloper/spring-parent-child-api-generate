package org.api.spring.generate.repository;

import org.api.spring.generate.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepo extends JpaRepository<Contract, Long> {

    @Query(value = "SELECT (max(id) + 1) as max FROM Contract")
    long max();

} 
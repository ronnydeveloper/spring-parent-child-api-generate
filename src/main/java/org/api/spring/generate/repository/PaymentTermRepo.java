package org.api.spring.generate.repository;

import org.api.spring.generate.entity.PaymentTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTermRepo extends JpaRepository<PaymentTerm, Long> {

    @Query(value = "SELECT (max(id) + 1) as max FROM PaymentTerm")
    long max();

} 
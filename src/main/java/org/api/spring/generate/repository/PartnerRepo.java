package org.api.spring.generate.repository;

import org.api.spring.generate.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepo extends JpaRepository<Partner, Long> {

    @Query(value = "SELECT (max(id) + 1) as max FROM Partner")
    long max();

} 
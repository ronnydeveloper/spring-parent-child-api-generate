package org.api.spring.generate.repository;

import org.api.spring.generate.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query(value = "SELECT (max(id) + 1) as max FROM Product")
    long max();

} 
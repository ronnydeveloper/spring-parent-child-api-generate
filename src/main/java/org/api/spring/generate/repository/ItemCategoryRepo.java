package org.api.spring.generate.repository;

import org.api.spring.generate.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryRepo extends JpaRepository<ItemCategory, Long> {

    @Query(value = "SELECT (max(id) + 1) as max FROM ItemCategory")
    long max();

} 
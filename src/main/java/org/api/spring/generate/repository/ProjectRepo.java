package org.api.spring.generate.repository;

import org.api.spring.generate.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {

    @Query(value = "SELECT (max(id) + 1) as max FROM Project")
    long max();

} 
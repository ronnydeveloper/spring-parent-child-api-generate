package org.api.spring.generate.repository;

import org.api.spring.generate.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT (max(id) + 1) as max FROM Employee")
    long max();

} 
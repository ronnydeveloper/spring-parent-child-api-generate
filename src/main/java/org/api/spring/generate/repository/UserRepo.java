package org.api.spring.generate.repository;

import org.api.spring.generate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = "SELECT (max(id) + 1) as max FROM User")
    long max();

} 
package core.api.inherit.springparentchildapigenerate.repository;

import core.api.inherit.springparentchildapigenerate.entity.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRepo extends JpaRepository<Tax, Long> {

    @Query(value = "SELECT (max(id) + 1) as max FROM Tax")
    long max();

} 
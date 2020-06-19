package core.api.inherit.springparentchildapigenerate.repository;

import core.api.inherit.springparentchildapigenerate.entity.StaticData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StaticDataRepo extends JpaRepository<StaticData, Long> {

    @Query(value = "SELECT (max(id) + 1) as max FROM StaticData")
    long max();

} 
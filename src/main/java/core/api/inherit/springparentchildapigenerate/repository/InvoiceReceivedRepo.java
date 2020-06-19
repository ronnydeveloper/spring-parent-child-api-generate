package core.api.inherit.springparentchildapigenerate.repository;

import core.api.inherit.springparentchildapigenerate.entity.InvoiceReceived;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceReceivedRepo extends JpaRepository<InvoiceReceived, Long> {

    @Query(value = "SELECT (max(id) + 1) as max FROM InvoiceReceived")
    long max();

} 
package core.api.inherit.springparentchildapigenerate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import core.api.inherit.springparentchildapigenerate.entity.Partner;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class InvoiceReceivedDTO { 

    private Long id;

    private Partner partnerID;

    private Date invoiceDate;

    private String invoiceNumber;

    private BigDecimal amount;

    private BigDecimal amountTax;

    private BigDecimal amountTotal;

    private String noFakturPajak;

    private Date receiptDate;

    private Date receiptDateFin;

    private String receiptStatus;

    private String note;


} 
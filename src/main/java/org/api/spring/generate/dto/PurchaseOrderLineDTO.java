package org.api.spring.generate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.api.spring.generate.entity.PurchaseOrder;

import java.math.BigDecimal;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PurchaseOrderLineDTO { 

    private Long lineId;

    private PurchaseOrder poID;

    private String name;

    private Integer productId;

    private Integer productQty;

    private String productUOM;

    private BigDecimal priceUnit;

    private BigDecimal amount;

    private String jenisHitungPPN;

    private BigDecimal taxAmount;

    private BigDecimal amountDPP;

    private Boolean invoicedFlag;

    private String lineStatus;

    private BigDecimal totalAmount;

    private String taxCode;


} 
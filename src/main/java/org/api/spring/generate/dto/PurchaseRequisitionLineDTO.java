package org.api.spring.generate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.api.spring.generate.entity.PurchaseRequisition;

import java.math.BigDecimal;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PurchaseRequisitionLineDTO { 

    private Long lineId;

    private PurchaseRequisition requisitionID;

    private String requestDesc;

    private Integer productId;

    private Integer productQty;

    private String productUOM;

    private BigDecimal priceUnit;

    private BigDecimal totalAmount;

    private Boolean invoicedFlag;

    private String lineStatus;


} 
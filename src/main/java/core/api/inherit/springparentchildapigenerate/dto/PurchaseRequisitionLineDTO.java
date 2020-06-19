package core.api.inherit.springparentchildapigenerate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import core.api.inherit.springparentchildapigenerate.entity.PurchaseRequisition;
import lombok.Builder;
import lombok.Data;

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

    private String invoicedFlag;

    private String lineStatus;


} 
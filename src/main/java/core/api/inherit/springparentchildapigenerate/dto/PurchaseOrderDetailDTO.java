package core.api.inherit.springparentchildapigenerate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import core.api.inherit.springparentchildapigenerate.entity.PurchaseOrder;
import core.api.inherit.springparentchildapigenerate.entity.PurchaseRequisition;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PurchaseOrderDetailDTO { 

    private Long poDetailId;

    private PurchaseOrder poID;

    private PurchaseRequisition requisitionID;


} 
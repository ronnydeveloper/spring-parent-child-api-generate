package org.api.spring.generate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.api.spring.generate.entity.PurchaseOrder;

import org.api.spring.generate.entity.PurchaseRequisition;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PurchaseOrderDetailDTO { 

    private Long poDetailId;

    private PurchaseOrder poID;

    private PurchaseRequisition requisitionID;


} 
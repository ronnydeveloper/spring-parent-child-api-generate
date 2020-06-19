package org.api.spring.generate.service;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.PurchaseOrderLine;
import org.api.spring.generate.dto.PurchaseOrderLineDTO;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface PurchaseOrderLineService {

    PurchaseOrderLine createOrUpdatePurchaseOrderLine(PurchaseOrderLine purchaseOrderLine);

    void deletePurchaseOrderLineById(Long lineId) throws EntityNotFoundException;

    PurchaseOrderLine getPurchaseOrderLineById(Long lineId) throws EntityNotFoundException;

    List<PurchaseOrderLineDTO> findAll();

    ApiResponse<List<PurchaseOrderLineDTO>> doView();

    ApiResponse doAdd(PurchaseOrderLine purchaseOrderLine);

    ApiResponse doEdit(PurchaseOrderLine purchaseOrderLine);

    ApiResponse doDelete(List<PurchaseOrderLine> purchaseOrderLineList);

    ApiResponse doPreview(PurchaseOrderLineDTO purchaseOrderLineDTO);

} 
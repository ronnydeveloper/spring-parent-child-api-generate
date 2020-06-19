package org.api.spring.generate.service;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.PurchaseRequisitionLine;
import org.api.spring.generate.dto.PurchaseRequisitionLineDTO;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface PurchaseRequisitionLineService {

    PurchaseRequisitionLine createOrUpdatePurchaseRequisitionLine(PurchaseRequisitionLine purchaseRequisitionLine);

    void deletePurchaseRequisitionLineById(Long lineId) throws EntityNotFoundException;

    PurchaseRequisitionLine getPurchaseRequisitionLineById(Long lineId) throws EntityNotFoundException;

    List<PurchaseRequisitionLineDTO> findAll();

    ApiResponse<List<PurchaseRequisitionLineDTO>> doView();

    ApiResponse doAdd(PurchaseRequisitionLine purchaseRequisitionLine);

    ApiResponse doEdit(PurchaseRequisitionLine purchaseRequisitionLine);

    ApiResponse doDelete(List<PurchaseRequisitionLine> purchaseRequisitionLineList);

    ApiResponse doPreview(PurchaseRequisitionLineDTO purchaseRequisitionLineDTO);

} 
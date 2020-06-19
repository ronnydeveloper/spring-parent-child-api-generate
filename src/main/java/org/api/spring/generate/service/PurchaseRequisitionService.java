package org.api.spring.generate.service;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.PurchaseRequisition;
import org.api.spring.generate.dto.PurchaseRequisitionDTO;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface PurchaseRequisitionService {

    PurchaseRequisition createOrUpdatePurchaseRequisition(PurchaseRequisition purchaseRequisition);

    void deletePurchaseRequisitionById(Long id) throws EntityNotFoundException;

    PurchaseRequisition getPurchaseRequisitionById(Long id) throws EntityNotFoundException;

    List<PurchaseRequisitionDTO> findAll();

    ApiResponse<List<PurchaseRequisitionDTO>> doView();

    ApiResponse doAdd(PurchaseRequisition purchaseRequisition);

    ApiResponse doEdit(PurchaseRequisition purchaseRequisition);

    ApiResponse doDelete(List<PurchaseRequisition> purchaseRequisitionList);

    ApiResponse doPreview(PurchaseRequisitionDTO purchaseRequisitionDTO);

} 
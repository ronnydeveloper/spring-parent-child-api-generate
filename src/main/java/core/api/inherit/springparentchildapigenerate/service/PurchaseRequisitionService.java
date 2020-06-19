package core.api.inherit.springparentchildapigenerate.service;

import core.api.inherit.springparentchildapigenerate.dto.PurchaseRequisitionDTO;
import core.api.inherit.springparentchildapigenerate.entity.PurchaseRequisition;
import id.co.ptap.circleaf.core.dto.ApiResponse;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface PurchaseRequisitionService {

    PurchaseRequisition initializationAssociation(PurchaseRequisition purchaseRequisition);

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
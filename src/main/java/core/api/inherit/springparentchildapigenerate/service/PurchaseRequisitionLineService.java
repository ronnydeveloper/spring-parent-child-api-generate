package core.api.inherit.springparentchildapigenerate.service;

import core.api.inherit.springparentchildapigenerate.dto.PurchaseRequisitionLineDTO;
import core.api.inherit.springparentchildapigenerate.entity.PurchaseRequisitionLine;
import id.co.ptap.circleaf.core.dto.ApiResponse;

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
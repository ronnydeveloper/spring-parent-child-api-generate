package core.api.inherit.springparentchildapigenerate.service;

import core.api.inherit.springparentchildapigenerate.dto.PurchaseOrderDTO;
import core.api.inherit.springparentchildapigenerate.entity.PurchaseOrder;
import id.co.ptap.circleaf.core.dto.ApiResponse;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface PurchaseOrderService {

    PurchaseOrder createOrUpdatePurchaseOrder(PurchaseOrder purchaseOrder);

    void deletePurchaseOrderById(Long id) throws EntityNotFoundException;

    PurchaseOrder getPurchaseOrderById(Long id) throws EntityNotFoundException;

    List<PurchaseOrderDTO> findAll();

    ApiResponse<List<PurchaseOrderDTO>> doView();

    ApiResponse doAdd(PurchaseOrder purchaseOrder);

    ApiResponse doEdit(PurchaseOrder purchaseOrder);

    ApiResponse doDelete(List<PurchaseOrder> purchaseOrderList);

    ApiResponse doPreview(PurchaseOrderDTO purchaseOrderDTO);

} 
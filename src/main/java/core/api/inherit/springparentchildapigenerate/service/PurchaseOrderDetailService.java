package core.api.inherit.springparentchildapigenerate.service;

import core.api.inherit.springparentchildapigenerate.dto.PurchaseOrderDetailDTO;
import core.api.inherit.springparentchildapigenerate.entity.PurchaseOrderDetail;
import id.co.ptap.circleaf.core.dto.ApiResponse;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface PurchaseOrderDetailService {

    PurchaseOrderDetail createOrUpdatePurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail);

    void deletePurchaseOrderDetailById(Long poDetailId) throws EntityNotFoundException;

    PurchaseOrderDetail getPurchaseOrderDetailById(Long poDetailId) throws EntityNotFoundException;

    List<PurchaseOrderDetailDTO> findAll();

    ApiResponse<List<PurchaseOrderDetailDTO>> doView();

    ApiResponse doAdd(PurchaseOrderDetail purchaseOrderDetail);

    ApiResponse doEdit(PurchaseOrderDetail purchaseOrderDetail);

    ApiResponse doDelete(List<PurchaseOrderDetail> purchaseOrderDetailList);

    ApiResponse doPreview(PurchaseOrderDetailDTO purchaseOrderDetailDTO);

} 
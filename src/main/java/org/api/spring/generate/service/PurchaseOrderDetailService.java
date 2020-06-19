package org.api.spring.generate.service;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.PurchaseOrderDetail;
import org.api.spring.generate.dto.PurchaseOrderDetailDTO;

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
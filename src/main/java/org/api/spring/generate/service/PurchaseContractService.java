package org.api.spring.generate.service;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.PurchaseContract;
import org.api.spring.generate.dto.PurchaseContractDTO;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface PurchaseContractService {

    PurchaseContract createOrUpdatePurchaseContract(PurchaseContract purchaseContract);

    void deletePurchaseContractById(Long id) throws EntityNotFoundException;

    PurchaseContract getPurchaseContractById(Long id) throws EntityNotFoundException;

    List<PurchaseContractDTO> findAll();

    ApiResponse<List<PurchaseContractDTO>> doView();

    ApiResponse doAdd(PurchaseContract purchaseContract);

    ApiResponse doEdit(PurchaseContract purchaseContract);

    ApiResponse doDelete(List<PurchaseContract> purchaseContractList);

    ApiResponse doPreview(PurchaseContractDTO purchaseContractDTO);

} 
package core.api.inherit.springparentchildapigenerate.service;

import core.api.inherit.springparentchildapigenerate.dto.PurchaseContractDTO;
import core.api.inherit.springparentchildapigenerate.entity.PurchaseContract;
import id.co.ptap.circleaf.core.dto.ApiResponse;

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
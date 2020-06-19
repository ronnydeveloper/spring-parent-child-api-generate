package org.api.spring.generate.service.impl;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import id.co.ptap.circleaf.core.enums.ResponseCode;
import org.api.spring.generate.entity.PurchaseRequisitionLine;
import org.api.spring.generate.dto.PurchaseRequisitionLineDTO;
import org.api.spring.generate.repository.PurchaseRequisitionLineRepo;
import org.api.spring.generate.service.PurchaseRequisitionLineService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.List;
import java.util.ArrayList;

@Service("purchaseRequisitionLineService")
public class PurchaseRequisitionLineServiceImpl implements PurchaseRequisitionLineService {

    static final Logger logger = Logger.getLogger(PurchaseRequisitionLineServiceImpl.class);

    @Autowired
    private PurchaseRequisitionLineRepo purchaseRequisitionLineRepository;

    @Override
    public PurchaseRequisitionLine createOrUpdatePurchaseRequisitionLine(PurchaseRequisitionLine purchaseRequisitionLine) {
         Optional<PurchaseRequisitionLine> purchaseRequisitionLineOptional = purchaseRequisitionLineRepository.findById(purchaseRequisitionLine.getLineId());
         if(purchaseRequisitionLineOptional.isPresent())
         {
             ModelMapper modelMapper = new ModelMapper();
             PurchaseRequisitionLine newPurchaseRequisitionLine = modelMapper.map(purchaseRequisitionLine, PurchaseRequisitionLine.class);
             newPurchaseRequisitionLine = purchaseRequisitionLineRepository.save(newPurchaseRequisitionLine);
             return newPurchaseRequisitionLine;
         } else {
             purchaseRequisitionLine = purchaseRequisitionLineRepository.save(purchaseRequisitionLine);
             return purchaseRequisitionLine;
         }
    }

    @Override
    public void deletePurchaseRequisitionLineById(Long lineId) throws EntityNotFoundException {
         Optional<PurchaseRequisitionLine> purchaseRequisitionLineOptional = purchaseRequisitionLineRepository.findById(lineId);
         if(purchaseRequisitionLineOptional.isPresent())
         {
            purchaseRequisitionLineRepository.deleteById(lineId);
         } else {
            throw new EntityNotFoundException("No PurchaseRequisitionLine record exist for given id");
         }
    }

    @Override
    public PurchaseRequisitionLine getPurchaseRequisitionLineById(Long lineId) throws EntityNotFoundException {
         Optional<PurchaseRequisitionLine> purchaseRequisitionLineOptional = purchaseRequisitionLineRepository.findById(lineId);
         if(purchaseRequisitionLineOptional.isPresent())
         {
            return purchaseRequisitionLineOptional.get();
         } else {
            throw new EntityNotFoundException("No PurchaseRequisitionLine record exist for given id");
         }
    }

    @Override
    public ApiResponse<List<PurchaseRequisitionLineDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<PurchaseRequisitionLineDTO> purchaseRequisitionLineList = this.findAll();
             apiResponse.setData(purchaseRequisitionLineList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(PurchaseRequisitionLine purchaseRequisitionLine) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (purchaseRequisitionLine == null) {
                throw new NullPointerException("PurchaseRequisitionLine cannot be null");
            }
            else {
                long max = 0;
                long count = purchaseRequisitionLineRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = purchaseRequisitionLineRepository.max();
                }
                purchaseRequisitionLine.setLineId(max);
            }
            Optional<PurchaseRequisitionLine> purchaseRequisitionLineOptional = purchaseRequisitionLineRepository.findById(purchaseRequisitionLine.getLineId());
            if(purchaseRequisitionLineOptional.isPresent()) {
                throw new EntityExistsException("There is a PurchaseRequisitionLine record exist for given lineId");
            } else {
                this.createOrUpdatePurchaseRequisitionLine(purchaseRequisitionLine);
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(PurchaseRequisitionLine purchaseRequisitionLine) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (purchaseRequisitionLine == null) {
                throw new NullPointerException("purchaseRequisitionLine cannot be null");
            }
            this.createOrUpdatePurchaseRequisitionLine(purchaseRequisitionLine);
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<PurchaseRequisitionLine> purchaseRequisitionLineList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (purchaseRequisitionLineList.size() < 1) {
                throw new NullPointerException("purchaseRequisitionLine cannot be null");
            }
            for (PurchaseRequisitionLine obj : purchaseRequisitionLineList) {
                this.deletePurchaseRequisitionLineById(obj.getLineId());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(PurchaseRequisitionLineDTO purchaseRequisitionLineDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getPurchaseRequisitionLineById(purchaseRequisitionLineDTO.getLineId()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

    @Override
    public List<PurchaseRequisitionLineDTO> findAll() {
        List<PurchaseRequisitionLineDTO> newList = new ArrayList<>();
        for(PurchaseRequisitionLine p : purchaseRequisitionLineRepository.findAll()) {
             PurchaseRequisitionLineDTO purchaserequisitionlineDTO = PurchaseRequisitionLineDTO.builder()
                     .lineId(p.getLineId())
                     .requisitionID(p.getRequisitionID())
                     .requestDesc(p.getRequestDesc())
                     .productId(p.getProductId())
                     .productQty(p.getProductQty())
                     .productUOM(p.getProductUOM())
                     .priceUnit(p.getPriceUnit())
                     .totalAmount(p.getTotalAmount())
                     .invoicedFlag(p.getInvoicedFlag())
                     .lineStatus(p.getLineStatus()).build();
             newList.add(purchaserequisitionlineDTO);
        }
        return newList;
    }

} 
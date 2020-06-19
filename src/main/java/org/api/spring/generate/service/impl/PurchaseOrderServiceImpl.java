package org.api.spring.generate.service.impl;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import id.co.ptap.circleaf.core.enums.ResponseCode;
import org.api.spring.generate.entity.PurchaseOrder;
import org.api.spring.generate.dto.PurchaseOrderDTO;
import org.api.spring.generate.repository.PurchaseOrderRepo;
import org.api.spring.generate.service.PurchaseOrderService;
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

@Service("purchaseOrderService")
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    static final Logger logger = Logger.getLogger(PurchaseOrderServiceImpl.class);

    @Autowired
    private PurchaseOrderRepo purchaseOrderRepository;

    @Override
    public PurchaseOrder createOrUpdatePurchaseOrder(PurchaseOrder purchaseOrder) {
         Optional<PurchaseOrder> purchaseOrderOptional = purchaseOrderRepository.findById(purchaseOrder.getId());
         if(purchaseOrderOptional.isPresent())
         {
             ModelMapper modelMapper = new ModelMapper();
             PurchaseOrder newPurchaseOrder = modelMapper.map(purchaseOrder, PurchaseOrder.class);
             newPurchaseOrder = purchaseOrderRepository.save(newPurchaseOrder);
             return newPurchaseOrder;
         } else {
             purchaseOrder = purchaseOrderRepository.save(purchaseOrder);
             return purchaseOrder;
         }
    }

    @Override
    public void deletePurchaseOrderById(Long id) throws EntityNotFoundException {
         Optional<PurchaseOrder> purchaseOrderOptional = purchaseOrderRepository.findById(id);
         if(purchaseOrderOptional.isPresent())
         {
            purchaseOrderRepository.deleteById(id);
         } else {
            throw new EntityNotFoundException("No PurchaseOrder record exist for given id");
         }
    }

    @Override
    public PurchaseOrder getPurchaseOrderById(Long id) throws EntityNotFoundException {
         Optional<PurchaseOrder> purchaseOrderOptional = purchaseOrderRepository.findById(id);
         if(purchaseOrderOptional.isPresent())
         {
            return purchaseOrderOptional.get();
         } else {
            throw new EntityNotFoundException("No PurchaseOrder record exist for given id");
         }
    }

    @Override
    public ApiResponse<List<PurchaseOrderDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<PurchaseOrderDTO> purchaseOrderList = this.findAll();
             apiResponse.setData(purchaseOrderList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(PurchaseOrder purchaseOrder) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (purchaseOrder == null) {
                throw new NullPointerException("PurchaseOrder cannot be null");
            }
            else {
                long max = 0;
                long count = purchaseOrderRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = purchaseOrderRepository.max();
                }
                purchaseOrder.setId(max);
            }
            Optional<PurchaseOrder> purchaseOrderOptional = purchaseOrderRepository.findById(purchaseOrder.getId());
            if(purchaseOrderOptional.isPresent()) {
                throw new EntityExistsException("There is a PurchaseOrder record exist for given id");
            } else {
                this.createOrUpdatePurchaseOrder(purchaseOrder);
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(PurchaseOrder purchaseOrder) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (purchaseOrder == null) {
                throw new NullPointerException("purchaseOrder cannot be null");
            }
            this.createOrUpdatePurchaseOrder(purchaseOrder);
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<PurchaseOrder> purchaseOrderList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (purchaseOrderList.size() < 1) {
                throw new NullPointerException("purchaseOrder cannot be null");
            }
            for (PurchaseOrder obj : purchaseOrderList) {
                this.deletePurchaseOrderById(obj.getId());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(PurchaseOrderDTO purchaseOrderDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getPurchaseOrderById(purchaseOrderDTO.getId()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

    @Override
    public List<PurchaseOrderDTO> findAll() {
        List<PurchaseOrderDTO> newList = new ArrayList<>();
        for(PurchaseOrder p : purchaseOrderRepository.findAll()) {
             PurchaseOrderDTO purchaseorderDTO = PurchaseOrderDTO.builder()
                     .id(p.getId())
                     .poNumber(p.getPoNumber())
                     .orderDate(p.getOrderDate())
                     .projectID(p.getProjectID())
                     .partnerID(p.getPartnerID())
                     .amount(p.getAmount())
                     .taxAmount(p.getTaxAmount())
                     .totalAmount(p.getTotalAmount())
                     .currency(p.getCurrency())
                     .poStatus(p.getPoStatus())
                     .bidDate(p.getBidDate())
                     .approvedDate(p.getApprovedDate())
                     .paymentTerm(p.getPaymentTerm())
                     .departmentID(p.getDepartmentID())
                     .companyID(p.getCompanyID())
                     .termNote(p.getTermNote())
                     .note(p.getNote()).build();
             newList.add(purchaseorderDTO);
        }
        return newList;
    }

} 
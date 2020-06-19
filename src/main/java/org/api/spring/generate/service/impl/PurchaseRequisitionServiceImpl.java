package org.api.spring.generate.service.impl;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import id.co.ptap.circleaf.core.enums.ResponseCode;
import org.api.spring.generate.entity.PurchaseRequisition;
import org.api.spring.generate.dto.PurchaseRequisitionDTO;
import org.api.spring.generate.repository.PurchaseRequisitionRepo;
import org.api.spring.generate.service.PurchaseRequisitionService;
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

@Service("purchaseRequisitionService")
public class PurchaseRequisitionServiceImpl implements PurchaseRequisitionService {

    static final Logger logger = Logger.getLogger(PurchaseRequisitionServiceImpl.class);

    @Autowired
    private PurchaseRequisitionRepo purchaseRequisitionRepository;

    @Override
    public PurchaseRequisition createOrUpdatePurchaseRequisition(PurchaseRequisition purchaseRequisition) {
         Optional<PurchaseRequisition> purchaseRequisitionOptional = purchaseRequisitionRepository.findById(purchaseRequisition.getId());
         if(purchaseRequisitionOptional.isPresent())
         {
             ModelMapper modelMapper = new ModelMapper();
             PurchaseRequisition newPurchaseRequisition = modelMapper.map(purchaseRequisition, PurchaseRequisition.class);
             newPurchaseRequisition = purchaseRequisitionRepository.save(newPurchaseRequisition);
             return newPurchaseRequisition;
         } else {
             purchaseRequisition = purchaseRequisitionRepository.save(purchaseRequisition);
             return purchaseRequisition;
         }
    }

    @Override
    public void deletePurchaseRequisitionById(Long id) throws EntityNotFoundException {
         Optional<PurchaseRequisition> purchaseRequisitionOptional = purchaseRequisitionRepository.findById(id);
         if(purchaseRequisitionOptional.isPresent())
         {
            purchaseRequisitionRepository.deleteById(id);
         } else {
            throw new EntityNotFoundException("No PurchaseRequisition record exist for given id");
         }
    }

    @Override
    public PurchaseRequisition getPurchaseRequisitionById(Long id) throws EntityNotFoundException {
         Optional<PurchaseRequisition> purchaseRequisitionOptional = purchaseRequisitionRepository.findById(id);
         if(purchaseRequisitionOptional.isPresent())
         {
            return purchaseRequisitionOptional.get();
         } else {
            throw new EntityNotFoundException("No PurchaseRequisition record exist for given id");
         }
    }

    @Override
    public ApiResponse<List<PurchaseRequisitionDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<PurchaseRequisitionDTO> purchaseRequisitionList = this.findAll();
             apiResponse.setData(purchaseRequisitionList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(PurchaseRequisition purchaseRequisition) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (purchaseRequisition == null) {
                throw new NullPointerException("PurchaseRequisition cannot be null");
            }
            else {
                long max = 0;
                long count = purchaseRequisitionRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = purchaseRequisitionRepository.max();
                }
                purchaseRequisition.setId(max);
            }
            Optional<PurchaseRequisition> purchaseRequisitionOptional = purchaseRequisitionRepository.findById(purchaseRequisition.getId());
            if(purchaseRequisitionOptional.isPresent()) {
                throw new EntityExistsException("There is a PurchaseRequisition record exist for given id");
            } else {
                this.createOrUpdatePurchaseRequisition(purchaseRequisition);
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(PurchaseRequisition purchaseRequisition) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (purchaseRequisition == null) {
                throw new NullPointerException("purchaseRequisition cannot be null");
            }
            this.createOrUpdatePurchaseRequisition(purchaseRequisition);
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<PurchaseRequisition> purchaseRequisitionList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (purchaseRequisitionList.size() < 1) {
                throw new NullPointerException("purchaseRequisition cannot be null");
            }
            for (PurchaseRequisition obj : purchaseRequisitionList) {
                this.deletePurchaseRequisitionById(obj.getId());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(PurchaseRequisitionDTO purchaseRequisitionDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getPurchaseRequisitionById(purchaseRequisitionDTO.getId()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

    @Override
    public List<PurchaseRequisitionDTO> findAll() {
        List<PurchaseRequisitionDTO> newList = new ArrayList<>();
        for(PurchaseRequisition p : purchaseRequisitionRepository.findAll()) {
             PurchaseRequisitionDTO purchaserequisitionDTO = PurchaseRequisitionDTO.builder()
                     .id(p.getId())
                     .prNumber(p.getPrNumber())
                     .name(p.getName())
                     .requestDate(p.getRequestDate())
                     .scheduleDate(p.getScheduleDate())
                     .requestId(p.getRequestId())
                     .prStatus(p.getPrStatus())
                     .poID(p.getPoID())
                     .inp01NoDoc(p.getInp01NoDoc())
                     .inp01TglTerbit(p.getInp01TglTerbit())
                     .inp01KadepProc(p.getInp01KadepProc())
                     .inp01Bod(p.getInp01Bod())
                     .inp01Atasan(p.getInp01Atasan())
                     .companyID(p.getCompanyID())
                     .createdUserId(p.getCreatedUserId())
                     .departmentID(p.getDepartmentID())
                     .currency(p.getCurrency())
                     .poDibuatFlag(p.getPoDibuatFlag())
                     .projectId(p.getProjectId())
                     .amount(p.getAmount()).build();
             newList.add(purchaserequisitionDTO);
        }
        return newList;
    }

} 
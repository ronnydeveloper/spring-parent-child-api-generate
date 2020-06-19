package org.api.spring.generate.service.impl;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import id.co.ptap.circleaf.core.enums.ResponseCode;
import org.api.spring.generate.entity.PurchaseContract;
import org.api.spring.generate.dto.PurchaseContractDTO;
import org.api.spring.generate.repository.PurchaseContractRepo;
import org.api.spring.generate.service.PurchaseContractService;
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

@Service("purchaseContractService")
public class PurchaseContractServiceImpl implements PurchaseContractService {

    static final Logger logger = Logger.getLogger(PurchaseContractServiceImpl.class);

    @Autowired
    private PurchaseContractRepo purchaseContractRepository;

    @Override
    public PurchaseContract createOrUpdatePurchaseContract(PurchaseContract purchaseContract) {
         Optional<PurchaseContract> purchaseContractOptional = purchaseContractRepository.findById(purchaseContract.getId());
         if(purchaseContractOptional.isPresent())
         {
             ModelMapper modelMapper = new ModelMapper();
             PurchaseContract newPurchaseContract = modelMapper.map(purchaseContract, PurchaseContract.class);
             newPurchaseContract = purchaseContractRepository.save(newPurchaseContract);
             return newPurchaseContract;
         } else {
             purchaseContract = purchaseContractRepository.save(purchaseContract);
             return purchaseContract;
         }
    }

    @Override
    public void deletePurchaseContractById(Long id) throws EntityNotFoundException {
         Optional<PurchaseContract> purchaseContractOptional = purchaseContractRepository.findById(id);
         if(purchaseContractOptional.isPresent())
         {
            purchaseContractRepository.deleteById(id);
         } else {
            throw new EntityNotFoundException("No PurchaseContract record exist for given id");
         }
    }

    @Override
    public PurchaseContract getPurchaseContractById(Long id) throws EntityNotFoundException {
         Optional<PurchaseContract> purchaseContractOptional = purchaseContractRepository.findById(id);
         if(purchaseContractOptional.isPresent())
         {
            return purchaseContractOptional.get();
         } else {
            throw new EntityNotFoundException("No PurchaseContract record exist for given id");
         }
    }

    @Override
    public ApiResponse<List<PurchaseContractDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<PurchaseContractDTO> purchaseContractList = this.findAll();
             apiResponse.setData(purchaseContractList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(PurchaseContract purchaseContract) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (purchaseContract == null) {
                throw new NullPointerException("PurchaseContract cannot be null");
            }
            else {
                long max = 0;
                long count = purchaseContractRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = purchaseContractRepository.max();
                }
                purchaseContract.setId(max);
            }
            Optional<PurchaseContract> purchaseContractOptional = purchaseContractRepository.findById(purchaseContract.getId());
            if(purchaseContractOptional.isPresent()) {
                throw new EntityExistsException("There is a PurchaseContract record exist for given id");
            } else {
                this.createOrUpdatePurchaseContract(purchaseContract);
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(PurchaseContract purchaseContract) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (purchaseContract == null) {
                throw new NullPointerException("purchaseContract cannot be null");
            }
            this.createOrUpdatePurchaseContract(purchaseContract);
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<PurchaseContract> purchaseContractList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (purchaseContractList.size() < 1) {
                throw new NullPointerException("purchaseContract cannot be null");
            }
            for (PurchaseContract obj : purchaseContractList) {
                this.deletePurchaseContractById(obj.getId());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(PurchaseContractDTO purchaseContractDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getPurchaseContractById(purchaseContractDTO.getId()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

    @Override
    public List<PurchaseContractDTO> findAll() {
        List<PurchaseContractDTO> newList = new ArrayList<>();
        for(PurchaseContract p : purchaseContractRepository.findAll()) {
             PurchaseContractDTO purchasecontractDTO = PurchaseContractDTO.builder()
                     .id(p.getId())
                     .contractNumber(p.getContractNumber())
                     .note(p.getNote())
                     .vendorID(p.getVendorID())
                     .startDate(p.getStartDate())
                     .endDate(p.getEndDate())
                     .customerID(p.getCustomerID())
                     .projectID(p.getProjectID()).build();
             newList.add(purchasecontractDTO);
        }
        return newList;
    }

} 
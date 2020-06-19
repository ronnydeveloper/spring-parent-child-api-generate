package core.api.inherit.springparentchildapigenerate.service.impl;

import core.api.inherit.springparentchildapigenerate.dto.PurchaseOrderDetailDTO;
import core.api.inherit.springparentchildapigenerate.entity.PurchaseOrderDetail;
import core.api.inherit.springparentchildapigenerate.repository.PurchaseOrderDetailRepo;
import core.api.inherit.springparentchildapigenerate.service.PurchaseOrderDetailService;
import id.co.ptap.circleaf.core.dto.ApiResponse;
import id.co.ptap.circleaf.core.enums.ResponseCode;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("purchaseOrderDetailService")
public class PurchaseOrderDetailServiceImpl implements PurchaseOrderDetailService {

    static final Logger logger = Logger.getLogger(PurchaseOrderDetailServiceImpl.class);

    @Autowired
    private PurchaseOrderDetailRepo purchaseOrderDetailRepository;

    @Override
    public PurchaseOrderDetail createOrUpdatePurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) {
         Optional<PurchaseOrderDetail> purchaseOrderDetailOptional = purchaseOrderDetailRepository.findById(purchaseOrderDetail.getPoDetailId());
         if(purchaseOrderDetailOptional.isPresent())
         {
             ModelMapper modelMapper = new ModelMapper();
             PurchaseOrderDetail newPurchaseOrderDetail = modelMapper.map(purchaseOrderDetail, PurchaseOrderDetail.class);
             newPurchaseOrderDetail = purchaseOrderDetailRepository.save(newPurchaseOrderDetail);
             return newPurchaseOrderDetail;
         } else {
             purchaseOrderDetail = purchaseOrderDetailRepository.save(purchaseOrderDetail);
             return purchaseOrderDetail;
         }
    }

    @Override
    public void deletePurchaseOrderDetailById(Long poDetailId) throws EntityNotFoundException {
         Optional<PurchaseOrderDetail> purchaseOrderDetailOptional = purchaseOrderDetailRepository.findById(poDetailId);
         if(purchaseOrderDetailOptional.isPresent())
         {
            purchaseOrderDetailRepository.deleteById(poDetailId);
         } else {
            throw new EntityNotFoundException("No PurchaseOrderDetail record exist for given id");
         }
    }

    @Override
    public PurchaseOrderDetail getPurchaseOrderDetailById(Long poDetailId) throws EntityNotFoundException {
         Optional<PurchaseOrderDetail> purchaseOrderDetailOptional = purchaseOrderDetailRepository.findById(poDetailId);
         if(purchaseOrderDetailOptional.isPresent())
         {
            return purchaseOrderDetailOptional.get();
         } else {
            throw new EntityNotFoundException("No PurchaseOrderDetail record exist for given id");
         }
    }

    @Override
    public ApiResponse<List<PurchaseOrderDetailDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<PurchaseOrderDetailDTO> purchaseOrderDetailList = this.findAll();
             apiResponse.setData(purchaseOrderDetailList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(PurchaseOrderDetail purchaseOrderDetail) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (purchaseOrderDetail == null) {
                throw new NullPointerException("PurchaseOrderDetail cannot be null");
            }
            else {
                long max = 0;
                long count = purchaseOrderDetailRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = purchaseOrderDetailRepository.max();
                }
                purchaseOrderDetail.setPoDetailId(max);
            }
            Optional<PurchaseOrderDetail> purchaseOrderDetailOptional = purchaseOrderDetailRepository.findById(purchaseOrderDetail.getPoDetailId());
            if(purchaseOrderDetailOptional.isPresent()) {
                throw new EntityExistsException("There is a PurchaseOrderDetail record exist for given poDetailId");
            } else {
                this.createOrUpdatePurchaseOrderDetail(purchaseOrderDetail);
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(PurchaseOrderDetail purchaseOrderDetail) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (purchaseOrderDetail == null) {
                throw new NullPointerException("purchaseOrderDetail cannot be null");
            }
            this.createOrUpdatePurchaseOrderDetail(purchaseOrderDetail);
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<PurchaseOrderDetail> purchaseOrderDetailList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (purchaseOrderDetailList.size() < 1) {
                throw new NullPointerException("purchaseOrderDetail cannot be null");
            }
            for (PurchaseOrderDetail obj : purchaseOrderDetailList) {
                this.deletePurchaseOrderDetailById(obj.getPoDetailId());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(PurchaseOrderDetailDTO purchaseOrderDetailDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getPurchaseOrderDetailById(purchaseOrderDetailDTO.getPoDetailId()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

    @Override
    public List<PurchaseOrderDetailDTO> findAll() {
        List<PurchaseOrderDetailDTO> newList = new ArrayList<>();
        for(PurchaseOrderDetail p : purchaseOrderDetailRepository.findAll()) {
             PurchaseOrderDetailDTO purchaseorderdetailDTO = PurchaseOrderDetailDTO.builder()
                     .poDetailId(p.getPoDetailId())
                     .poID(p.getPoID())
                     .requisitionID(p.getRequisitionID()).build();
             newList.add(purchaseorderdetailDTO);
        }
        return newList;
    }

} 
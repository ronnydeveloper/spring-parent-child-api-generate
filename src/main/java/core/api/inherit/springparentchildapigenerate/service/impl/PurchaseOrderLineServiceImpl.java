package core.api.inherit.springparentchildapigenerate.service.impl;

import core.api.inherit.springparentchildapigenerate.dto.PurchaseOrderLineDTO;
import core.api.inherit.springparentchildapigenerate.entity.PurchaseOrderLine;
import core.api.inherit.springparentchildapigenerate.repository.PurchaseOrderLineRepo;
import core.api.inherit.springparentchildapigenerate.service.PurchaseOrderLineService;
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

@Service("purchaseOrderLineService")
public class PurchaseOrderLineServiceImpl implements PurchaseOrderLineService {

    static final Logger logger = Logger.getLogger(PurchaseOrderLineServiceImpl.class);

    @Autowired
    private PurchaseOrderLineRepo purchaseOrderLineRepository;

    @Override
    public PurchaseOrderLine createOrUpdatePurchaseOrderLine(PurchaseOrderLine purchaseOrderLine) {
         Optional<PurchaseOrderLine> purchaseOrderLineOptional = purchaseOrderLineRepository.findById(purchaseOrderLine.getLineId());
         if(purchaseOrderLineOptional.isPresent())
         {
             ModelMapper modelMapper = new ModelMapper();
             PurchaseOrderLine newPurchaseOrderLine = modelMapper.map(purchaseOrderLine, PurchaseOrderLine.class);
             newPurchaseOrderLine = purchaseOrderLineRepository.save(newPurchaseOrderLine);
             return newPurchaseOrderLine;
         } else {
             purchaseOrderLine = purchaseOrderLineRepository.save(purchaseOrderLine);
             return purchaseOrderLine;
         }
    }

    @Override
    public void deletePurchaseOrderLineById(Long lineId) throws EntityNotFoundException {
         Optional<PurchaseOrderLine> purchaseOrderLineOptional = purchaseOrderLineRepository.findById(lineId);
         if(purchaseOrderLineOptional.isPresent())
         {
            purchaseOrderLineRepository.deleteById(lineId);
         } else {
            throw new EntityNotFoundException("No PurchaseOrderLine record exist for given id");
         }
    }

    @Override
    public PurchaseOrderLine getPurchaseOrderLineById(Long lineId) throws EntityNotFoundException {
         Optional<PurchaseOrderLine> purchaseOrderLineOptional = purchaseOrderLineRepository.findById(lineId);
         if(purchaseOrderLineOptional.isPresent())
         {
            return purchaseOrderLineOptional.get();
         } else {
            throw new EntityNotFoundException("No PurchaseOrderLine record exist for given id");
         }
    }

    @Override
    public ApiResponse<List<PurchaseOrderLineDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<PurchaseOrderLineDTO> purchaseOrderLineList = this.findAll();
             apiResponse.setData(purchaseOrderLineList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(PurchaseOrderLine purchaseOrderLine) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (purchaseOrderLine == null) {
                throw new NullPointerException("PurchaseOrderLine cannot be null");
            }
            else {
                long max = 0;
                long count = purchaseOrderLineRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = purchaseOrderLineRepository.max();
                }
                purchaseOrderLine.setLineId(max);
            }
            Optional<PurchaseOrderLine> purchaseOrderLineOptional = purchaseOrderLineRepository.findById(purchaseOrderLine.getLineId());
            if(purchaseOrderLineOptional.isPresent()) {
                throw new EntityExistsException("There is a PurchaseOrderLine record exist for given lineId");
            } else {
                this.createOrUpdatePurchaseOrderLine(purchaseOrderLine);
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(PurchaseOrderLine purchaseOrderLine) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (purchaseOrderLine == null) {
                throw new NullPointerException("purchaseOrderLine cannot be null");
            }
            this.createOrUpdatePurchaseOrderLine(purchaseOrderLine);
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<PurchaseOrderLine> purchaseOrderLineList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (purchaseOrderLineList.size() < 1) {
                throw new NullPointerException("purchaseOrderLine cannot be null");
            }
            for (PurchaseOrderLine obj : purchaseOrderLineList) {
                this.deletePurchaseOrderLineById(obj.getLineId());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(PurchaseOrderLineDTO purchaseOrderLineDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getPurchaseOrderLineById(purchaseOrderLineDTO.getLineId()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

    @Override
    public List<PurchaseOrderLineDTO> findAll() {
        List<PurchaseOrderLineDTO> newList = new ArrayList<>();
        for(PurchaseOrderLine p : purchaseOrderLineRepository.findAll()) {
             PurchaseOrderLineDTO purchaseorderlineDTO = PurchaseOrderLineDTO.builder()
                     .lineId(p.getLineId())
                     .poID(p.getPoID())
                     .name(p.getName())
                     .productId(p.getProductId())
                     .productQty(p.getProductQty())
                     .productUOM(p.getProductUOM())
                     .priceUnit(p.getPriceUnit())
                     .amount(p.getAmount())
                     .jenisHitungPPN(p.getJenisHitungPPN())
                     .taxAmount(p.getTaxAmount())
                     .amountDPP(p.getAmountDPP())
                     .invoicedFlag(p.getInvoicedFlag())
                     .lineStatus(p.getLineStatus())
                     .totalAmount(p.getTotalAmount())
                     .taxCode(p.getTaxCode()).build();
             newList.add(purchaseorderlineDTO);
        }
        return newList;
    }

} 
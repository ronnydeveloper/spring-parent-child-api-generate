package core.api.inherit.springparentchildapigenerate.service.impl;

import core.api.inherit.springparentchildapigenerate.dto.InvoiceReceivedDTO;
import core.api.inherit.springparentchildapigenerate.entity.InvoiceReceived;
import core.api.inherit.springparentchildapigenerate.repository.InvoiceReceivedRepo;
import core.api.inherit.springparentchildapigenerate.service.InvoiceReceivedService;
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

@Service("invoiceReceivedService")
public class InvoiceReceivedServiceImpl implements InvoiceReceivedService {

    static final Logger logger = Logger.getLogger(InvoiceReceivedServiceImpl.class);

    @Autowired
    private InvoiceReceivedRepo invoiceReceivedRepository;

    @Override
    public InvoiceReceived createOrUpdateInvoiceReceived(InvoiceReceived invoiceReceived) {
         Optional<InvoiceReceived> invoiceReceivedOptional = invoiceReceivedRepository.findById(invoiceReceived.getId());
         if(invoiceReceivedOptional.isPresent())
         {
             ModelMapper modelMapper = new ModelMapper();
             InvoiceReceived newInvoiceReceived = modelMapper.map(invoiceReceived, InvoiceReceived.class);
             newInvoiceReceived = invoiceReceivedRepository.save(newInvoiceReceived);
             return newInvoiceReceived;
         } else {
             invoiceReceived = invoiceReceivedRepository.save(invoiceReceived);
             return invoiceReceived;
         }
    }

    @Override
    public void deleteInvoiceReceivedById(Long id) throws EntityNotFoundException {
         Optional<InvoiceReceived> invoiceReceivedOptional = invoiceReceivedRepository.findById(id);
         if(invoiceReceivedOptional.isPresent())
         {
            invoiceReceivedRepository.deleteById(id);
         } else {
            throw new EntityNotFoundException("No InvoiceReceived record exist for given id");
         }
    }

    @Override
    public InvoiceReceived getInvoiceReceivedById(Long id) throws EntityNotFoundException {
         Optional<InvoiceReceived> invoiceReceivedOptional = invoiceReceivedRepository.findById(id);
         if(invoiceReceivedOptional.isPresent())
         {
            return invoiceReceivedOptional.get();
         } else {
            throw new EntityNotFoundException("No InvoiceReceived record exist for given id");
         }
    }

    @Override
    public ApiResponse<List<InvoiceReceivedDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<InvoiceReceivedDTO> invoiceReceivedList = this.findAll();
             apiResponse.setData(invoiceReceivedList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(InvoiceReceived invoiceReceived) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (invoiceReceived == null) {
                throw new NullPointerException("InvoiceReceived cannot be null");
            }
            else {
                long max = 0;
                long count = invoiceReceivedRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = invoiceReceivedRepository.max();
                }
                invoiceReceived.setId(max);
            }
            Optional<InvoiceReceived> invoiceReceivedOptional = invoiceReceivedRepository.findById(invoiceReceived.getId());
            if(invoiceReceivedOptional.isPresent()) {
                throw new EntityExistsException("There is a InvoiceReceived record exist for given id");
            } else {
                this.createOrUpdateInvoiceReceived(invoiceReceived);
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(InvoiceReceived invoiceReceived) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (invoiceReceived == null) {
                throw new NullPointerException("invoiceReceived cannot be null");
            }
            this.createOrUpdateInvoiceReceived(invoiceReceived);
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<InvoiceReceived> invoiceReceivedList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (invoiceReceivedList.size() < 1) {
                throw new NullPointerException("invoiceReceived cannot be null");
            }
            for (InvoiceReceived obj : invoiceReceivedList) {
                this.deleteInvoiceReceivedById(obj.getId());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(InvoiceReceivedDTO invoiceReceivedDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getInvoiceReceivedById(invoiceReceivedDTO.getId()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

    @Override
    public List<InvoiceReceivedDTO> findAll() {
        List<InvoiceReceivedDTO> newList = new ArrayList<>();
        for(InvoiceReceived p : invoiceReceivedRepository.findAll()) {
             InvoiceReceivedDTO invoicereceivedDTO = InvoiceReceivedDTO.builder()
                     .id(p.getId())
                     .partnerID(p.getPartnerID())
                     .invoiceDate(p.getInvoiceDate())
                     .invoiceNumber(p.getInvoiceNumber())
                     .amount(p.getAmount())
                     .amountTax(p.getAmountTax())
                     .amountTotal(p.getAmountTotal())
                     .noFakturPajak(p.getNoFakturPajak())
                     .receiptDate(p.getReceiptDate())
                     .receiptDateFin(p.getReceiptDateFin())
                     .receiptStatus(p.getReceiptStatus())
                     .note(p.getNote()).build();
             newList.add(invoicereceivedDTO);
        }
        return newList;
    }

} 
package org.api.spring.generate.service.impl;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import id.co.ptap.circleaf.core.enums.ResponseCode;
import org.api.spring.generate.entity.PaymentTerm;
import org.api.spring.generate.dto.PaymentTermDTO;
import org.api.spring.generate.repository.PaymentTermRepo;
import org.api.spring.generate.service.PaymentTermService;
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

@Service("paymentTermService")
public class PaymentTermServiceImpl implements PaymentTermService {

    static final Logger logger = Logger.getLogger(PaymentTermServiceImpl.class);

    @Autowired
    private PaymentTermRepo paymentTermRepository;

    @Override
    public PaymentTerm createOrUpdatePaymentTerm(PaymentTerm paymentTerm) {
         Optional<PaymentTerm> paymentTermOptional = paymentTermRepository.findById(paymentTerm.getId());
         if(paymentTermOptional.isPresent())
         {
             ModelMapper modelMapper = new ModelMapper();
             PaymentTerm newPaymentTerm = modelMapper.map(paymentTerm, PaymentTerm.class);
             newPaymentTerm = paymentTermRepository.save(newPaymentTerm);
             return newPaymentTerm;
         } else {
             paymentTerm = paymentTermRepository.save(paymentTerm);
             return paymentTerm;
         }
    }

    @Override
    public void deletePaymentTermById(Long id) throws EntityNotFoundException {
         Optional<PaymentTerm> paymentTermOptional = paymentTermRepository.findById(id);
         if(paymentTermOptional.isPresent())
         {
            paymentTermRepository.deleteById(id);
         } else {
            throw new EntityNotFoundException("No PaymentTerm record exist for given id");
         }
    }

    @Override
    public PaymentTerm getPaymentTermById(Long id) throws EntityNotFoundException {
         Optional<PaymentTerm> paymentTermOptional = paymentTermRepository.findById(id);
         if(paymentTermOptional.isPresent())
         {
            return paymentTermOptional.get();
         } else {
            throw new EntityNotFoundException("No PaymentTerm record exist for given id");
         }
    }

    @Override
    public ApiResponse<List<PaymentTermDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<PaymentTermDTO> paymentTermList = this.findAll();
             apiResponse.setData(paymentTermList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(PaymentTerm paymentTerm) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (paymentTerm == null) {
                throw new NullPointerException("PaymentTerm cannot be null");
            }
            else {
                long max = 0;
                long count = paymentTermRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = paymentTermRepository.max();
                }
                paymentTerm.setId(max);
            }
            Optional<PaymentTerm> paymentTermOptional = paymentTermRepository.findById(paymentTerm.getId());
            if(paymentTermOptional.isPresent()) {
                throw new EntityExistsException("There is a PaymentTerm record exist for given id");
            } else {
                this.createOrUpdatePaymentTerm(paymentTerm);
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(PaymentTerm paymentTerm) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (paymentTerm == null) {
                throw new NullPointerException("paymentTerm cannot be null");
            }
            this.createOrUpdatePaymentTerm(paymentTerm);
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<PaymentTerm> paymentTermList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (paymentTermList.size() < 1) {
                throw new NullPointerException("paymentTerm cannot be null");
            }
            for (PaymentTerm obj : paymentTermList) {
                this.deletePaymentTermById(obj.getId());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(PaymentTermDTO paymentTermDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getPaymentTermById(paymentTermDTO.getId()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

    @Override
    public List<PaymentTermDTO> findAll() {
        List<PaymentTermDTO> newList = new ArrayList<>();
        for(PaymentTerm p : paymentTermRepository.findAll()) {
             PaymentTermDTO paymenttermDTO = PaymentTermDTO.builder()
                     .id(p.getId())
                     .paymentTermCode(p.getPaymentTermCode())
                     .name(p.getName())
                     .jumlah(p.getJumlah())
                     .termType(p.getTermType())
                     .calendarFlag(p.getCalendarFlag()).build();
             newList.add(paymenttermDTO);
        }
        return newList;
    }

} 
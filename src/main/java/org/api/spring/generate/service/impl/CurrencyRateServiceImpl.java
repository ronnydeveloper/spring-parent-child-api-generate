package org.api.spring.generate.service.impl;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import id.co.ptap.circleaf.core.enums.ResponseCode;
import org.api.spring.generate.entity.CurrencyRate;
import org.api.spring.generate.dto.CurrencyRateDTO;
import org.api.spring.generate.repository.CurrencyRateRepo;
import org.api.spring.generate.service.CurrencyRateService;
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

@Service("currencyRateService")
public class CurrencyRateServiceImpl implements CurrencyRateService {

    static final Logger logger = Logger.getLogger(CurrencyRateServiceImpl.class);

    @Autowired
    private CurrencyRateRepo currencyRateRepository;

    @Override
    public CurrencyRate createOrUpdateCurrencyRate(CurrencyRate currencyRate) {
         Optional<CurrencyRate> currencyRateOptional = currencyRateRepository.findById(currencyRate.getCurrency());
         if(currencyRateOptional.isPresent())
         {
             ModelMapper modelMapper = new ModelMapper();
             CurrencyRate newCurrencyRate = modelMapper.map(currencyRate, CurrencyRate.class);
             newCurrencyRate = currencyRateRepository.save(newCurrencyRate);
             return newCurrencyRate;
         } else {
             currencyRate = currencyRateRepository.save(currencyRate);
             return currencyRate;
         }
    }

    @Override
    public void deleteCurrencyRateById(String currency) throws EntityNotFoundException {
         Optional<CurrencyRate> currencyRateOptional = currencyRateRepository.findById(currency);
         if(currencyRateOptional.isPresent())
         {
            currencyRateRepository.deleteById(currency);
         } else {
            throw new EntityNotFoundException("No CurrencyRate record exist for given id");
         }
    }

    @Override
    public CurrencyRate getCurrencyRateById(String currency) throws EntityNotFoundException {
         Optional<CurrencyRate> currencyRateOptional = currencyRateRepository.findById(currency);
         if(currencyRateOptional.isPresent())
         {
            return currencyRateOptional.get();
         } else {
            throw new EntityNotFoundException("No CurrencyRate record exist for given id");
         }
    }

    @Override
    public ApiResponse<List<CurrencyRateDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<CurrencyRateDTO> currencyRateList = this.findAll();
             apiResponse.setData(currencyRateList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(CurrencyRate currencyRate) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (currencyRate == null) {
                throw new NullPointerException("CurrencyRate cannot be null");
            }
            else {
                long max = 0;
                long count = currencyRateRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = currencyRateRepository.max();
                }
                currencyRate.setCurrency(max);
            }
            Optional<CurrencyRate> currencyRateOptional = currencyRateRepository.findById(currencyRate.getCurrency());
            if(currencyRateOptional.isPresent()) {
                throw new EntityExistsException("There is a CurrencyRate record exist for given currency");
            } else {
                this.createOrUpdateCurrencyRate(currencyRate);
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(CurrencyRate currencyRate) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (currencyRate == null) {
                throw new NullPointerException("currencyRate cannot be null");
            }
            this.createOrUpdateCurrencyRate(currencyRate);
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<CurrencyRate> currencyRateList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (currencyRateList.size() < 1) {
                throw new NullPointerException("currencyRate cannot be null");
            }
            for (CurrencyRate obj : currencyRateList) {
                this.deleteCurrencyRateById(obj.getCurrency());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(CurrencyRateDTO currencyRateDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getCurrencyRateById(currencyRateDTO.getCurrency()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

    @Override
    public List<CurrencyRateDTO> findAll() {
        List<CurrencyRateDTO> newList = new ArrayList<>();
        for(CurrencyRate p : currencyRateRepository.findAll()) {
             CurrencyRateDTO currencyrateDTO = CurrencyRateDTO.builder()
                     .currency(p.getCurrency())
                     .date(p.getDate())
                     .companyRate(p.getCompanyRate())
                     .kursJual(p.getKursJual())
                     .kursTengah(p.getKursTengah())
                     .kursBeli(p.getKursBeli()).build();
             newList.add(currencyrateDTO);
        }
        return newList;
    }

} 
package core.api.inherit.springparentchildapigenerate.service.impl;

import core.api.inherit.springparentchildapigenerate.dto.TaxDTO;
import core.api.inherit.springparentchildapigenerate.entity.Tax;
import core.api.inherit.springparentchildapigenerate.repository.TaxRepo;
import core.api.inherit.springparentchildapigenerate.service.TaxService;
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

@Service("taxService")
public class TaxServiceImpl implements TaxService {

    static final Logger logger = Logger.getLogger(TaxServiceImpl.class);

    @Autowired
    private TaxRepo taxRepository;

    @Override
    public Tax createOrUpdateTax(Tax tax) {
         Optional<Tax> taxOptional = taxRepository.findById(tax.getId());
         if(taxOptional.isPresent())
         {
             ModelMapper modelMapper = new ModelMapper();
             Tax newTax = modelMapper.map(tax, Tax.class);
             newTax = taxRepository.save(newTax);
             return newTax;
         } else {
             tax = taxRepository.save(tax);
             return tax;
         }
    }

    @Override
    public void deleteTaxById(Long id) throws EntityNotFoundException {
         Optional<Tax> taxOptional = taxRepository.findById(id);
         if(taxOptional.isPresent())
         {
            taxRepository.deleteById(id);
         } else {
            throw new EntityNotFoundException("No Tax record exist for given id");
         }
    }

    @Override
    public Tax getTaxById(Long id) throws EntityNotFoundException {
         Optional<Tax> taxOptional = taxRepository.findById(id);
         if(taxOptional.isPresent())
         {
            return taxOptional.get();
         } else {
            throw new EntityNotFoundException("No Tax record exist for given id");
         }
    }

    @Override
    public ApiResponse<List<TaxDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<TaxDTO> taxList = this.findAll();
             apiResponse.setData(taxList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(Tax tax) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (tax == null) {
                throw new NullPointerException("Tax cannot be null");
            }
            else {
                long max = 0;
                long count = taxRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = taxRepository.max();
                }
                tax.setId(max);
            }
            Optional<Tax> taxOptional = taxRepository.findById(tax.getId());
            if(taxOptional.isPresent()) {
                throw new EntityExistsException("There is a Tax record exist for given id");
            } else {
                this.createOrUpdateTax(tax);
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(Tax tax) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (tax == null) {
                throw new NullPointerException("tax cannot be null");
            }
            this.createOrUpdateTax(tax);
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<Tax> taxList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (taxList.size() < 1) {
                throw new NullPointerException("tax cannot be null");
            }
            for (Tax obj : taxList) {
                this.deleteTaxById(obj.getId());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(TaxDTO taxDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getTaxById(taxDTO.getId()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

    @Override
    public List<TaxDTO> findAll() {
        List<TaxDTO> newList = new ArrayList<>();
        for(Tax p : taxRepository.findAll()) {
             TaxDTO taxDTO = TaxDTO.builder()
                     .id(p.getId())
                     .taxCode(p.getTaxCode())
                     .name(p.getName())
                     .taxValue(p.getTaxValue())
                     .calcType(p.getCalcType())
                     .companyID(p.getCompanyID())
                     .defaultCurrency(p.getDefaultCurrency())
                     .accountCode(p.getAccountCode())
                     .modulType(p.getModulType())
                     .baseTaxFlag(p.getBaseTaxFlag()).build();
             newList.add(taxDTO);
        }
        return newList;
    }

} 
package org.api.spring.generate.service.impl;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import id.co.ptap.circleaf.core.enums.ResponseCode;
import org.api.spring.generate.entity.Fiscal;
import org.api.spring.generate.dto.FiscalDTO;
import org.api.spring.generate.repository.FiscalRepo;
import org.api.spring.generate.service.FiscalService;
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

@Service("fiscalService")
public class FiscalServiceImpl implements FiscalService {

    static final Logger logger = Logger.getLogger(FiscalServiceImpl.class);

    @Autowired
    private FiscalRepo fiscalRepository;

    @Override
    public Fiscal createOrUpdateFiscal(Fiscal fiscal) {
         Optional<Fiscal> fiscalOptional = fiscalRepository.findById(fiscal.getId());
         if(fiscalOptional.isPresent())
         {
             ModelMapper modelMapper = new ModelMapper();
             Fiscal newFiscal = modelMapper.map(fiscal, Fiscal.class);
             newFiscal = fiscalRepository.save(newFiscal);
             return newFiscal;
         } else {
             fiscal = fiscalRepository.save(fiscal);
             return fiscal;
         }
    }

    @Override
    public void deleteFiscalById(Long id) throws EntityNotFoundException {
         Optional<Fiscal> fiscalOptional = fiscalRepository.findById(id);
         if(fiscalOptional.isPresent())
         {
            fiscalRepository.deleteById(id);
         } else {
            throw new EntityNotFoundException("No Fiscal record exist for given id");
         }
    }

    @Override
    public Fiscal getFiscalById(Long id) throws EntityNotFoundException {
         Optional<Fiscal> fiscalOptional = fiscalRepository.findById(id);
         if(fiscalOptional.isPresent())
         {
            return fiscalOptional.get();
         } else {
            throw new EntityNotFoundException("No Fiscal record exist for given id");
         }
    }

    @Override
    public ApiResponse<List<FiscalDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<FiscalDTO> fiscalList = this.findAll();
             apiResponse.setData(fiscalList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(Fiscal fiscal) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (fiscal == null) {
                throw new NullPointerException("Fiscal cannot be null");
            }
            else {
                long max = 0;
                long count = fiscalRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = fiscalRepository.max();
                }
                fiscal.setId(max);
            }
            Optional<Fiscal> fiscalOptional = fiscalRepository.findById(fiscal.getId());
            if(fiscalOptional.isPresent()) {
                throw new EntityExistsException("There is a Fiscal record exist for given id");
            } else {
                this.createOrUpdateFiscal(fiscal);
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(Fiscal fiscal) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (fiscal == null) {
                throw new NullPointerException("fiscal cannot be null");
            }
            this.createOrUpdateFiscal(fiscal);
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<Fiscal> fiscalList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (fiscalList.size() < 1) {
                throw new NullPointerException("fiscal cannot be null");
            }
            for (Fiscal obj : fiscalList) {
                this.deleteFiscalById(obj.getId());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(FiscalDTO fiscalDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getFiscalById(fiscalDTO.getId()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

    @Override
    public List<FiscalDTO> findAll() {
        List<FiscalDTO> newList = new ArrayList<>();
        for(Fiscal p : fiscalRepository.findAll()) {
             FiscalDTO fiscalDTO = FiscalDTO.builder()
                     .id(p.getId())
                     .fiscalYear(p.getFiscalYear())
                     .startDate(p.getStartDate())
                     .endDate(p.getEndDate())
                     .note(p.getNote()).build();
             newList.add(fiscalDTO);
        }
        return newList;
    }

} 
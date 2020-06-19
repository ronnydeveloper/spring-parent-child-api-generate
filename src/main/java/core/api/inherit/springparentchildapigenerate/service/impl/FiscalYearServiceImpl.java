package core.api.inherit.springparentchildapigenerate.service.impl;

import core.api.inherit.springparentchildapigenerate.dto.FiscalYearDTO;
import core.api.inherit.springparentchildapigenerate.entity.FiscalYear;
import core.api.inherit.springparentchildapigenerate.repository.FiscalYearRepo;
import core.api.inherit.springparentchildapigenerate.service.FiscalYearService;
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

@Service("fiscalYearService")
public class FiscalYearServiceImpl implements FiscalYearService {

    static final Logger logger = Logger.getLogger(FiscalYearServiceImpl.class);

    @Autowired
    private FiscalYearRepo fiscalYearRepository;

    @Override
    public FiscalYear createOrUpdateFiscalYear(FiscalYear fiscalYear) {
         Optional<FiscalYear> fiscalYearOptional = fiscalYearRepository.findById(fiscalYear.getId());
         if(fiscalYearOptional.isPresent())
         {
             ModelMapper modelMapper = new ModelMapper();
             FiscalYear newFiscalYear = modelMapper.map(fiscalYear, FiscalYear.class);
             newFiscalYear = fiscalYearRepository.save(newFiscalYear);
             return newFiscalYear;
         } else {
             fiscalYear = fiscalYearRepository.save(fiscalYear);
             return fiscalYear;
         }
    }

    @Override
    public void deleteFiscalYearById(Long id) throws EntityNotFoundException {
         Optional<FiscalYear> fiscalYearOptional = fiscalYearRepository.findById(id);
         if(fiscalYearOptional.isPresent())
         {
            fiscalYearRepository.deleteById(id);
         } else {
            throw new EntityNotFoundException("No FiscalYear record exist for given id");
         }
    }

    @Override
    public FiscalYear getFiscalYearById(Long id) throws EntityNotFoundException {
         Optional<FiscalYear> fiscalYearOptional = fiscalYearRepository.findById(id);
         if(fiscalYearOptional.isPresent())
         {
            return fiscalYearOptional.get();
         } else {
            throw new EntityNotFoundException("No FiscalYear record exist for given id");
         }
    }

    @Override
    public ApiResponse<List<FiscalYearDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<FiscalYearDTO> fiscalYearList = this.findAll();
             apiResponse.setData(fiscalYearList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(FiscalYear fiscalYear) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (fiscalYear == null) {
                throw new NullPointerException("FiscalYear cannot be null");
            }
            else {
                long max = 0;
                long count = fiscalYearRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = fiscalYearRepository.max();
                }
                fiscalYear.setId(max);
            }
            Optional<FiscalYear> fiscalYearOptional = fiscalYearRepository.findById(fiscalYear.getId());
            if(fiscalYearOptional.isPresent()) {
                throw new EntityExistsException("There is a FiscalYear record exist for given id");
            } else {
                this.createOrUpdateFiscalYear(fiscalYear);
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(FiscalYear fiscalYear) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (fiscalYear == null) {
                throw new NullPointerException("fiscalYear cannot be null");
            }
            this.createOrUpdateFiscalYear(fiscalYear);
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<FiscalYear> fiscalYearList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (fiscalYearList.size() < 1) {
                throw new NullPointerException("fiscalYear cannot be null");
            }
            for (FiscalYear obj : fiscalYearList) {
                this.deleteFiscalYearById(obj.getId());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(FiscalYearDTO fiscalYearDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getFiscalYearById(fiscalYearDTO.getId()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

    @Override
    public List<FiscalYearDTO> findAll() {
        List<FiscalYearDTO> newList = new ArrayList<>();
        for(FiscalYear p : fiscalYearRepository.findAll()) {
             FiscalYearDTO fiscalyearDTO = FiscalYearDTO.builder()
                     .id(p.getId())
                     .fiscalYear(p.getFiscalYear())
                     .periode(p.getPeriode())
                     .startDate(p.getStartDate())
                     .endDate(p.getEndDate())
                     .status(p.getStatus())
                     .fiscalID(p.getFiscalID()).build();
             newList.add(fiscalyearDTO);
        }
        return newList;
    }

} 
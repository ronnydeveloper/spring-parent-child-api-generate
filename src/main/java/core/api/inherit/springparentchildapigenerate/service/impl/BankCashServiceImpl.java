package core.api.inherit.springparentchildapigenerate.service.impl;

import core.api.inherit.springparentchildapigenerate.dto.BankCashDTO;
import core.api.inherit.springparentchildapigenerate.entity.BankCash;
import core.api.inherit.springparentchildapigenerate.repository.BankCashRepo;
import core.api.inherit.springparentchildapigenerate.service.BankCashService;
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

@Service("bankCashService")
public class BankCashServiceImpl implements BankCashService {

    static final Logger logger = Logger.getLogger(BankCashServiceImpl.class);

    @Autowired
    private BankCashRepo bankCashRepository;

    @Override
    public BankCash createOrUpdateBankCash(BankCash bankCash) {
         Optional<BankCash> bankCashOptional = bankCashRepository.findById(bankCash.getId());
         if(bankCashOptional.isPresent())
         {
             ModelMapper modelMapper = new ModelMapper();
             BankCash newBankCash = modelMapper.map(bankCash, BankCash.class);
             newBankCash = bankCashRepository.save(newBankCash);
             return newBankCash;
         } else {
             bankCash = bankCashRepository.save(bankCash);
             return bankCash;
         }
    }

    @Override
    public void deleteBankCashById(Long id) throws EntityNotFoundException {
         Optional<BankCash> bankCashOptional = bankCashRepository.findById(id);
         if(bankCashOptional.isPresent())
         {
            bankCashRepository.deleteById(id);
         } else {
            throw new EntityNotFoundException("No BankCash record exist for given id");
         }
    }

    @Override
    public BankCash getBankCashById(Long id) throws EntityNotFoundException {
         Optional<BankCash> bankCashOptional = bankCashRepository.findById(id);
         if(bankCashOptional.isPresent())
         {
            return bankCashOptional.get();
         } else {
            throw new EntityNotFoundException("No BankCash record exist for given id");
         }
    }

    @Override
    public ApiResponse<List<BankCashDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<BankCashDTO> bankCashList = this.findAll();
             apiResponse.setData(bankCashList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(BankCash bankCash) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (bankCash == null) {
                throw new NullPointerException("BankCash cannot be null");
            }
            else {
                long max = 0;
                long count = bankCashRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = bankCashRepository.max();
                }
                bankCash.setId(max);
            }
            Optional<BankCash> bankCashOptional = bankCashRepository.findById(bankCash.getId());
            if(bankCashOptional.isPresent()) {
                throw new EntityExistsException("There is a BankCash record exist for given id");
            } else {
                this.createOrUpdateBankCash(bankCash);
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(BankCash bankCash) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (bankCash == null) {
                throw new NullPointerException("bankCash cannot be null");
            }
            this.createOrUpdateBankCash(bankCash);
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<BankCash> bankCashList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (bankCashList.size() < 1) {
                throw new NullPointerException("bankCash cannot be null");
            }
            for (BankCash obj : bankCashList) {
                this.deleteBankCashById(obj.getId());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(BankCashDTO bankCashDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getBankCashById(bankCashDTO.getId()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

    @Override
    public List<BankCashDTO> findAll() {
        List<BankCashDTO> newList = new ArrayList<>();
        for(BankCash p : bankCashRepository.findAll()) {
             BankCashDTO bankcashDTO = BankCashDTO.builder()
                     .id(p.getId())
                     .name(p.getName())
                     .accountCode(p.getAccountCode())
                     .currency(p.getCurrency())
                     .voucherFlag(p.getVoucherFlag())
                     .pettyCashFlag(p.getPettyCashFlag())
                     .journalType(p.getJournalType()).build();
             newList.add(bankcashDTO);
        }
        return newList;
    }

} 
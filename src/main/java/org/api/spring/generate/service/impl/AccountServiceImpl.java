package org.api.spring.generate.service.impl;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import id.co.ptap.circleaf.core.enums.ResponseCode;
import org.api.spring.generate.entity.Account;
import org.api.spring.generate.dto.AccountDTO;
import org.api.spring.generate.repository.AccountRepo;
import org.api.spring.generate.service.AccountService;
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

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    static final Logger logger = Logger.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountRepo accountRepository;

    @Override
    public Account createOrUpdateAccount(Account account) {
         Optional<Account> accountOptional = accountRepository.findById(account.getId());
         if(accountOptional.isPresent())
         {
             ModelMapper modelMapper = new ModelMapper();
             Account newAccount = modelMapper.map(account, Account.class);
             newAccount = accountRepository.save(newAccount);
             return newAccount;
         } else {
             account = accountRepository.save(account);
             return account;
         }
    }

    @Override
    public void deleteAccountById(Long id) throws EntityNotFoundException {
         Optional<Account> accountOptional = accountRepository.findById(id);
         if(accountOptional.isPresent())
         {
            accountRepository.deleteById(id);
         } else {
            throw new EntityNotFoundException("No Account record exist for given id");
         }
    }

    @Override
    public Account getAccountById(Long id) throws EntityNotFoundException {
         Optional<Account> accountOptional = accountRepository.findById(id);
         if(accountOptional.isPresent())
         {
            return accountOptional.get();
         } else {
            throw new EntityNotFoundException("No Account record exist for given id");
         }
    }

    @Override
    public ApiResponse<List<AccountDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<AccountDTO> accountList = this.findAll();
             apiResponse.setData(accountList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(Account account) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (account == null) {
                throw new NullPointerException("Account cannot be null");
            }
            else {
                long max = 0;
                long count = accountRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = accountRepository.max();
                }
                account.setId(max);
            }
            Optional<Account> accountOptional = accountRepository.findById(account.getId());
            if(accountOptional.isPresent()) {
                throw new EntityExistsException("There is a Account record exist for given id");
            } else {
                this.createOrUpdateAccount(account);
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(Account account) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (account == null) {
                throw new NullPointerException("account cannot be null");
            }
            this.createOrUpdateAccount(account);
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<Account> accountList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (accountList.size() < 1) {
                throw new NullPointerException("account cannot be null");
            }
            for (Account obj : accountList) {
                this.deleteAccountById(obj.getId());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(AccountDTO accountDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getAccountById(accountDTO.getId()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

    @Override
    public List<AccountDTO> findAll() {
        List<AccountDTO> newList = new ArrayList<>();
        for(Account p : accountRepository.findAll()) {
             AccountDTO accountDTO = AccountDTO.builder()
                     .id(p.getId())
                     .code(p.getCode())
                     .name(p.getName())
                     .parent(p.getParent())
                     .currency(p.getCurrency())
                     .internalType(p.getInternalType())
                     .accountType(p.getAccountType())
                     .activeStatus(p.getActiveStatus())
                     .companyID(p.getCompanyID())
                     .note(p.getNote())
                     .children(p.getChildren()).build();
             newList.add(accountDTO);
        }
        return newList;
    }

} 
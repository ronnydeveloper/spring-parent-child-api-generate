package org.api.spring.generate.service;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.Account;
import org.api.spring.generate.dto.AccountDTO;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface AccountService {

    Account createOrUpdateAccount(Account account);

    void deleteAccountById(Long id) throws EntityNotFoundException;

    Account getAccountById(Long id) throws EntityNotFoundException;

    List<AccountDTO> findAll();

    ApiResponse<List<AccountDTO>> doView();

    ApiResponse doAdd(Account account);

    ApiResponse doEdit(Account account);

    ApiResponse doDelete(List<Account> accountList);

    ApiResponse doPreview(AccountDTO accountDTO);

} 
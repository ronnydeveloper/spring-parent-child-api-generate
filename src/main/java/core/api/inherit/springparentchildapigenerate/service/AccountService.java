package core.api.inherit.springparentchildapigenerate.service;

import core.api.inherit.springparentchildapigenerate.dto.AccountDTO;
import core.api.inherit.springparentchildapigenerate.entity.Account;
import core.api.inherit.springparentchildapigenerate.util.response.ApiResponse;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface AccountService {

    Account createOrUpdateAccount(Account account);

    void deleteAccountById(Long id) throws EntityNotFoundException;

    Account getAccountById(Long id) throws EntityNotFoundException;

    List<Account> findAllAccount();

    List<AccountDTO> findAll();

    ApiResponse<List<AccountDTO>> doView();

    ApiResponse doAdd(Account account);

    ApiResponse doEdit(Account account);

    ApiResponse doDelete(List<Account> accountList);

    ApiResponse doPreview(AccountDTO accountDTO);

} 
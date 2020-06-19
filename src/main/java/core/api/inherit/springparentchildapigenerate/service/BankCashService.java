package core.api.inherit.springparentchildapigenerate.service;

import core.api.inherit.springparentchildapigenerate.dto.BankCashDTO;
import core.api.inherit.springparentchildapigenerate.entity.BankCash;
import id.co.ptap.circleaf.core.dto.ApiResponse;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface BankCashService {

    BankCash createOrUpdateBankCash(BankCash bankCash);

    void deleteBankCashById(Long id) throws EntityNotFoundException;

    BankCash getBankCashById(Long id) throws EntityNotFoundException;

    List<BankCashDTO> findAll();

    ApiResponse<List<BankCashDTO>> doView();

    ApiResponse doAdd(BankCash bankCash);

    ApiResponse doEdit(BankCash bankCash);

    ApiResponse doDelete(List<BankCash> bankCashList);

    ApiResponse doPreview(BankCashDTO bankCashDTO);

} 
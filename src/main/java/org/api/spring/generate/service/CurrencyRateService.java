package org.api.spring.generate.service;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.CurrencyRate;
import org.api.spring.generate.dto.CurrencyRateDTO;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface CurrencyRateService {

    CurrencyRate createOrUpdateCurrencyRate(CurrencyRate currencyRate);

    void deleteCurrencyRateById(String currency) throws EntityNotFoundException;

    CurrencyRate getCurrencyRateById(String currency) throws EntityNotFoundException;

    List<CurrencyRateDTO> findAll();

    ApiResponse<List<CurrencyRateDTO>> doView();

    ApiResponse doAdd(CurrencyRate currencyRate);

    ApiResponse doEdit(CurrencyRate currencyRate);

    ApiResponse doDelete(List<CurrencyRate> currencyRateList);

    ApiResponse doPreview(CurrencyRateDTO currencyRateDTO);

} 
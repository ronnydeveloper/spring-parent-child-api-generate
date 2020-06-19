package org.api.spring.generate.service;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.FiscalYear;
import org.api.spring.generate.dto.FiscalYearDTO;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface FiscalYearService {

    FiscalYear createOrUpdateFiscalYear(FiscalYear fiscalYear);

    void deleteFiscalYearById(Long id) throws EntityNotFoundException;

    FiscalYear getFiscalYearById(Long id) throws EntityNotFoundException;

    List<FiscalYearDTO> findAll();

    ApiResponse<List<FiscalYearDTO>> doView();

    ApiResponse doAdd(FiscalYear fiscalYear);

    ApiResponse doEdit(FiscalYear fiscalYear);

    ApiResponse doDelete(List<FiscalYear> fiscalYearList);

    ApiResponse doPreview(FiscalYearDTO fiscalYearDTO);

} 
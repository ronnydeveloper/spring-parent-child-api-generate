package core.api.inherit.springparentchildapigenerate.service;

import core.api.inherit.springparentchildapigenerate.dto.FiscalYearDTO;
import core.api.inherit.springparentchildapigenerate.entity.FiscalYear;
import id.co.ptap.circleaf.core.dto.ApiResponse;

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
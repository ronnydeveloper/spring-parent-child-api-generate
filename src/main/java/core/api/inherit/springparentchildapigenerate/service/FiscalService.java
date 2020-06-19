package core.api.inherit.springparentchildapigenerate.service;

import core.api.inherit.springparentchildapigenerate.dto.FiscalDTO;
import core.api.inherit.springparentchildapigenerate.entity.Fiscal;
import id.co.ptap.circleaf.core.dto.ApiResponse;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface FiscalService {

    Fiscal createOrUpdateFiscal(Fiscal fiscal);

    void deleteFiscalById(Long id) throws EntityNotFoundException;

    Fiscal getFiscalById(Long id) throws EntityNotFoundException;

    List<FiscalDTO> findAll();

    ApiResponse<List<FiscalDTO>> doView();

    ApiResponse doAdd(Fiscal fiscal);

    ApiResponse doEdit(Fiscal fiscal);

    ApiResponse doDelete(List<Fiscal> fiscalList);

    ApiResponse doPreview(FiscalDTO fiscalDTO);

} 
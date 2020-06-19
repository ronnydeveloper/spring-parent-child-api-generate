package core.api.inherit.springparentchildapigenerate.service;

import core.api.inherit.springparentchildapigenerate.dto.TaxDTO;
import core.api.inherit.springparentchildapigenerate.entity.Tax;
import id.co.ptap.circleaf.core.dto.ApiResponse;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface TaxService {

    Tax createOrUpdateTax(Tax tax);

    void deleteTaxById(Long id) throws EntityNotFoundException;

    Tax getTaxById(Long id) throws EntityNotFoundException;

    List<TaxDTO> findAll();

    ApiResponse<List<TaxDTO>> doView();

    ApiResponse doAdd(Tax tax);

    ApiResponse doEdit(Tax tax);

    ApiResponse doDelete(List<Tax> taxList);

    ApiResponse doPreview(TaxDTO taxDTO);

} 
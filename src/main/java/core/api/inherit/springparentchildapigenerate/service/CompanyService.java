package core.api.inherit.springparentchildapigenerate.service;

import core.api.inherit.springparentchildapigenerate.dto.CompanyDTO;
import core.api.inherit.springparentchildapigenerate.entity.Company;
import core.api.inherit.springparentchildapigenerate.util.response.ApiResponse;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface CompanyService {

    Company createOrUpdateCompany(Company company);

    void deleteCompanyById(Long id) throws EntityNotFoundException;

    Company getCompanyById(Long id) throws EntityNotFoundException;

    List<CompanyDTO> findAll();

    ApiResponse<List<CompanyDTO>> doView();

    ApiResponse doAdd(Company company);

    ApiResponse doEdit(Company company);

    ApiResponse doDelete(List<CompanyDTO> companyDTOList);

    ApiResponse doPreview(CompanyDTO companyDTO);

} 
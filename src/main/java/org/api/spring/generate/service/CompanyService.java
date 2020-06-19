package org.api.spring.generate.service;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.Company;
import org.api.spring.generate.dto.CompanyDTO;

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

    ApiResponse doDelete(List<Company> companyList);

    ApiResponse doPreview(CompanyDTO companyDTO);

} 
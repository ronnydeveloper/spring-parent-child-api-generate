package core.api.inherit.springparentchildapigenerate.service.impl;

import core.api.inherit.springparentchildapigenerate.dto.CompanyDTO;
import core.api.inherit.springparentchildapigenerate.entity.Company;
import core.api.inherit.springparentchildapigenerate.repository.CompanyRepo;
import core.api.inherit.springparentchildapigenerate.service.CompanyService;
import core.api.inherit.springparentchildapigenerate.util.enums.ResponseCode;
import core.api.inherit.springparentchildapigenerate.util.response.ApiResponse;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

    static final Logger logger = Logger.getLogger(CompanyServiceImpl.class);

    @Autowired
    private CompanyRepo companyRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Company createOrUpdateCompany(Company company) {
         Optional<Company> companyOptional = companyRepository.findById(company.getId());
         if(companyOptional.isPresent())
         {
             modelMapper = new ModelMapper();
             Company newCompany = modelMapper.map(company, Company.class);
             newCompany = companyRepository.save(newCompany);
             return newCompany;
         } else {
             company = companyRepository.save(company);
             return company;
         }
    }

    @Override
    public void deleteCompanyById(Long id) throws EntityNotFoundException {
         Optional<Company> companyOptional = companyRepository.findById(id);
         if(companyOptional.isPresent())
         {
            companyRepository.deleteById(id);
         } else {
            throw new EntityNotFoundException("No Company record exist for given id");
         }
    }

    @Override
    public Company getCompanyById(Long id) throws EntityNotFoundException {
         Optional<Company> companyOptional = companyRepository.findById(id);
         if(companyOptional.isPresent())
         {
            return companyOptional.get();
         } else {
            throw new EntityNotFoundException("No Company record exist for given id");
         }
    }

    @Override
    public List<CompanyDTO> findAll() {
        List<CompanyDTO> newList = new ArrayList<>();
        for(Company p : companyRepository.findAll()) {
            CompanyDTO companyDTO = CompanyDTO.builder()
                    .id(p.getId())
                    .name(p.getName())
                    .coaName(p.getCoaName())
                    .persons(p.getPersons())
                    .accounts(p.getAccounts()).build();
            newList.add(companyDTO);
        }

        return newList;
    }

    @Override
    public ApiResponse<List<CompanyDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<CompanyDTO> companyList = this.findAll();
             apiResponse.setData(companyList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(Company company) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (company == null) {
                throw new NullPointerException("Company cannot be null");
            }
            else {
                long max = 0;
                long count = companyRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = companyRepository.max();
                }
                company.setId(max);
            }
            Optional<Company> companyOptional = companyRepository.findById(company.getId());
            if(companyOptional.isPresent()) {
                throw new EntityExistsException("There is a Company record exist for given id");
            } else {
                this.createOrUpdateCompany(company);
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(Company company) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (company == null) {
                throw new NullPointerException("company cannot be null");
            }
            this.createOrUpdateCompany(company);
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<CompanyDTO> companyDTOList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (companyDTOList.size() < 1) {
                throw new NullPointerException("company cannot be null");
            }
            for (CompanyDTO obj : companyDTOList) {
                this.deleteCompanyById(obj.getId());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(CompanyDTO companyDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getCompanyById(companyDTO.getId()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

} 
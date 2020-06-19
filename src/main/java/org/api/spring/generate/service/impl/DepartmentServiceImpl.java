package org.api.spring.generate.service.impl;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import id.co.ptap.circleaf.core.enums.ResponseCode;
import org.api.spring.generate.entity.Department;
import org.api.spring.generate.dto.DepartmentDTO;
import org.api.spring.generate.repository.DepartmentRepo;
import org.api.spring.generate.service.DepartmentService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.List;
import java.util.ArrayList;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    static final Logger logger = Logger.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentRepo departmentRepository;

    @Override
    public Department createOrUpdateDepartment(Department department) {
         Optional<Department> departmentOptional = departmentRepository.findById(department.getId());
         if(departmentOptional.isPresent())
         {
             ModelMapper modelMapper = new ModelMapper();
             Department newDepartment = modelMapper.map(department, Department.class);
             newDepartment = departmentRepository.save(newDepartment);
             return newDepartment;
         } else {
             department = departmentRepository.save(department);
             return department;
         }
    }

    @Override
    public void deleteDepartmentById(Long id) throws EntityNotFoundException {
         Optional<Department> departmentOptional = departmentRepository.findById(id);
         if(departmentOptional.isPresent())
         {
            departmentRepository.deleteById(id);
         } else {
            throw new EntityNotFoundException("No Department record exist for given id");
         }
    }

    @Override
    public Department getDepartmentById(Long id) throws EntityNotFoundException {
         Optional<Department> departmentOptional = departmentRepository.findById(id);
         if(departmentOptional.isPresent())
         {
            return departmentOptional.get();
         } else {
            throw new EntityNotFoundException("No Department record exist for given id");
         }
    }

    @Override
    public ApiResponse<List<DepartmentDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<DepartmentDTO> departmentList = this.findAll();
             apiResponse.setData(departmentList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(Department department) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (department == null) {
                throw new NullPointerException("Department cannot be null");
            }
            else {
                long max = 0;
                long count = departmentRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = departmentRepository.max();
                }
                department.setId(max);
            }
            Optional<Department> departmentOptional = departmentRepository.findById(department.getId());
            if(departmentOptional.isPresent()) {
                throw new EntityExistsException("There is a Department record exist for given id");
            } else {
                this.createOrUpdateDepartment(department);
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(Department department) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (department == null) {
                throw new NullPointerException("department cannot be null");
            }
            this.createOrUpdateDepartment(department);
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<Department> departmentList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (departmentList.size() < 1) {
                throw new NullPointerException("department cannot be null");
            }
            for (Department obj : departmentList) {
                this.deleteDepartmentById(obj.getId());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(DepartmentDTO departmentDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getDepartmentById(departmentDTO.getId()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

    @Override
    public List<DepartmentDTO> findAll() {
        List<DepartmentDTO> newList = new ArrayList<>();
        for(Department p : departmentRepository.findAll()) {
             DepartmentDTO departmentDTO = DepartmentDTO.builder()
                     .id(p.getId())
                     .code(p.getCode())
                     .name(p.getName())
                     .parent(p.getParent())
                     .companyID(p.getCompanyID()).build();
             newList.add(departmentDTO);
        }
        return newList;
    }

} 
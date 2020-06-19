package org.api.spring.generate.service;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.Department;
import org.api.spring.generate.dto.DepartmentDTO;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface DepartmentService {

    Department createOrUpdateDepartment(Department department);

    void deleteDepartmentById(Long id) throws EntityNotFoundException;

    Department getDepartmentById(Long id) throws EntityNotFoundException;

    List<DepartmentDTO> findAll();

    ApiResponse<List<DepartmentDTO>> doView();

    ApiResponse doAdd(Department department);

    ApiResponse doEdit(Department department);

    ApiResponse doDelete(List<Department> departmentList);

    ApiResponse doPreview(DepartmentDTO departmentDTO);

} 
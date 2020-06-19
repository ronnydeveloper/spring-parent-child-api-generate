package core.api.inherit.springparentchildapigenerate.service;

import core.api.inherit.springparentchildapigenerate.dto.DepartmentDTO;
import core.api.inherit.springparentchildapigenerate.entity.Department;
import id.co.ptap.circleaf.core.dto.ApiResponse;

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
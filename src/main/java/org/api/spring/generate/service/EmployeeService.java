package org.api.spring.generate.service;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.Employee;
import org.api.spring.generate.dto.EmployeeDTO;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface EmployeeService {

    Employee createOrUpdateEmployee(Employee employee);

    void deleteEmployeeById(Long id) throws EntityNotFoundException;

    Employee getEmployeeById(Long id) throws EntityNotFoundException;

    List<EmployeeDTO> findAll();

    ApiResponse<List<EmployeeDTO>> doView();

    ApiResponse doAdd(Employee employee);

    ApiResponse doEdit(Employee employee);

    ApiResponse doDelete(List<Employee> employeeList);

    ApiResponse doPreview(EmployeeDTO employeeDTO);

} 
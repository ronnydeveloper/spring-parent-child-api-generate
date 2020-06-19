package core.api.inherit.springparentchildapigenerate.service;

import core.api.inherit.springparentchildapigenerate.dto.EmployeeDTO;
import core.api.inherit.springparentchildapigenerate.entity.Employee;
import id.co.ptap.circleaf.core.dto.ApiResponse;

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
package core.api.inherit.springparentchildapigenerate.service.impl;

import core.api.inherit.springparentchildapigenerate.dto.EmployeeDTO;
import core.api.inherit.springparentchildapigenerate.entity.Employee;
import core.api.inherit.springparentchildapigenerate.repository.EmployeeRepo;
import core.api.inherit.springparentchildapigenerate.service.EmployeeService;
import id.co.ptap.circleaf.core.dto.ApiResponse;
import id.co.ptap.circleaf.core.enums.ResponseCode;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    static final Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepo employeeRepository;

    @Override
    public Employee createOrUpdateEmployee(Employee employee) {
         Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());
         if(employeeOptional.isPresent())
         {
             ModelMapper modelMapper = new ModelMapper();
             Employee newEmployee = modelMapper.map(employee, Employee.class);
             newEmployee = employeeRepository.save(newEmployee);
             return newEmployee;
         } else {
             employee = employeeRepository.save(employee);
             return employee;
         }
    }

    @Override
    public void deleteEmployeeById(Long id) throws EntityNotFoundException {
         Optional<Employee> employeeOptional = employeeRepository.findById(id);
         if(employeeOptional.isPresent())
         {
            employeeRepository.deleteById(id);
         } else {
            throw new EntityNotFoundException("No Employee record exist for given id");
         }
    }

    @Override
    public Employee getEmployeeById(Long id) throws EntityNotFoundException {
         Optional<Employee> employeeOptional = employeeRepository.findById(id);
         if(employeeOptional.isPresent())
         {
            return employeeOptional.get();
         } else {
            throw new EntityNotFoundException("No Employee record exist for given id");
         }
    }

    @Override
    public ApiResponse<List<EmployeeDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<EmployeeDTO> employeeList = this.findAll();
             apiResponse.setData(employeeList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(Employee employee) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (employee == null) {
                throw new NullPointerException("Employee cannot be null");
            }
            else {
                long max = 0;
                long count = employeeRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = employeeRepository.max();
                }
                employee.setId(max);
            }
            Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());
            if(employeeOptional.isPresent()) {
                throw new EntityExistsException("There is a Employee record exist for given id");
            } else {
                this.createOrUpdateEmployee(employee);
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(Employee employee) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (employee == null) {
                throw new NullPointerException("employee cannot be null");
            }
            this.createOrUpdateEmployee(employee);
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<Employee> employeeList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (employeeList.size() < 1) {
                throw new NullPointerException("employee cannot be null");
            }
            for (Employee obj : employeeList) {
                this.deleteEmployeeById(obj.getId());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(EmployeeDTO employeeDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getEmployeeById(employeeDTO.getId()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

    @Override
    public List<EmployeeDTO> findAll() {
        List<EmployeeDTO> newList = new ArrayList<>();
        for(Employee p : employeeRepository.findAll()) {
             EmployeeDTO employeeDTO = EmployeeDTO.builder()
                     .id(p.getId())
                     .nik(p.getNik())
                     .name(p.getName()).build();
             newList.add(employeeDTO);
        }
        return newList;
    }

} 
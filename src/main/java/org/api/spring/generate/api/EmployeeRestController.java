package org.api.spring.generate.api;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.Employee;
import org.api.spring.generate.dto.EmployeeDTO;
import org.api.spring.generate.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {

    final static Logger logger = Logger.getLogger(EmployeeRestController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping({"/list"})
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> list() {
        logger.info("Call list function");
        ApiResponse<List<EmployeeDTO>> apiResponse = this.employeeService.doView();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @RequestMapping(value = "/create",
            produces = "application/json",
            method= RequestMethod.POST)
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> create(@RequestBody Employee employee) {
        logger.info("Call create function");
        ApiResponse apiResponse = this.employeeService.doAdd(employee);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/edit")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> edit(@RequestBody Employee employee) {
        logger.info("Call edit function");
        ApiResponse apiResponse = this.employeeService.doEdit(employee);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/remove")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> remove(@RequestBody List<Employee> employeeList) {
        logger.info("Call remove function");
        ApiResponse apiResponse = this.employeeService.doDelete(employeeList);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/preview")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> preview(@RequestBody EmployeeDTO employeeDTO) {
        logger.info("Call preview function");
        ApiResponse apiResponse = this.employeeService.doPreview(employeeDTO);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

} 
package org.api.spring.generate.api;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.Company;
import org.api.spring.generate.dto.CompanyDTO;
import org.api.spring.generate.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyRestController {

    final static Logger logger = Logger.getLogger(CompanyRestController.class);

    @Autowired
    private CompanyService companyService;

    @GetMapping({"/list"})
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> list() {
        logger.info("Call list function");
        ApiResponse<List<CompanyDTO>> apiResponse = this.companyService.doView();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @RequestMapping(value = "/create",
            produces = "application/json",
            method= RequestMethod.POST)
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> create(@RequestBody Company company) {
        logger.info("Call create function");
        ApiResponse apiResponse = this.companyService.doAdd(company);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/edit")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> edit(@RequestBody Company company) {
        logger.info("Call edit function");
        ApiResponse apiResponse = this.companyService.doEdit(company);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/remove")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> remove(@RequestBody List<Company> companyList) {
        logger.info("Call remove function");
        ApiResponse apiResponse = this.companyService.doDelete(companyList);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/preview")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> preview(@RequestBody CompanyDTO companyDTO) {
        logger.info("Call preview function");
        ApiResponse apiResponse = this.companyService.doPreview(companyDTO);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

} 
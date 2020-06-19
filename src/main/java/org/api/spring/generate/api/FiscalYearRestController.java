package org.api.spring.generate.api;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.FiscalYear;
import org.api.spring.generate.dto.FiscalYearDTO;
import org.api.spring.generate.service.FiscalYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fiscalyear")
public class FiscalYearRestController {

    final static Logger logger = Logger.getLogger(FiscalYearRestController.class);

    @Autowired
    private FiscalYearService fiscalYearService;

    @GetMapping({"/list"})
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> list() {
        logger.info("Call list function");
        ApiResponse<List<FiscalYearDTO>> apiResponse = this.fiscalYearService.doView();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @RequestMapping(value = "/create",
            produces = "application/json",
            method= RequestMethod.POST)
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> create(@RequestBody FiscalYear fiscalYear) {
        logger.info("Call create function");
        ApiResponse apiResponse = this.fiscalYearService.doAdd(fiscalYear);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/edit")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> edit(@RequestBody FiscalYear fiscalYear) {
        logger.info("Call edit function");
        ApiResponse apiResponse = this.fiscalYearService.doEdit(fiscalYear);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/remove")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> remove(@RequestBody List<FiscalYear> fiscalYearList) {
        logger.info("Call remove function");
        ApiResponse apiResponse = this.fiscalYearService.doDelete(fiscalYearList);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/preview")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> preview(@RequestBody FiscalYearDTO fiscalYearDTO) {
        logger.info("Call preview function");
        ApiResponse apiResponse = this.fiscalYearService.doPreview(fiscalYearDTO);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

} 
package org.api.spring.generate.api;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.BankCash;
import org.api.spring.generate.dto.BankCashDTO;
import org.api.spring.generate.service.BankCashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bankcash")
public class BankCashRestController {

    final static Logger logger = Logger.getLogger(BankCashRestController.class);

    @Autowired
    private BankCashService bankCashService;

    @GetMapping({"/list"})
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> list() {
        logger.info("Call list function");
        ApiResponse<List<BankCashDTO>> apiResponse = this.bankCashService.doView();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @RequestMapping(value = "/create",
            produces = "application/json",
            method= RequestMethod.POST)
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> create(@RequestBody BankCash bankCash) {
        logger.info("Call create function");
        ApiResponse apiResponse = this.bankCashService.doAdd(bankCash);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/edit")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> edit(@RequestBody BankCash bankCash) {
        logger.info("Call edit function");
        ApiResponse apiResponse = this.bankCashService.doEdit(bankCash);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/remove")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> remove(@RequestBody List<BankCash> bankCashList) {
        logger.info("Call remove function");
        ApiResponse apiResponse = this.bankCashService.doDelete(bankCashList);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/preview")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> preview(@RequestBody BankCashDTO bankCashDTO) {
        logger.info("Call preview function");
        ApiResponse apiResponse = this.bankCashService.doPreview(bankCashDTO);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

} 
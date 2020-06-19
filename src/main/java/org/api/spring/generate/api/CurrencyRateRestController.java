package org.api.spring.generate.api;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.CurrencyRate;
import org.api.spring.generate.dto.CurrencyRateDTO;
import org.api.spring.generate.service.CurrencyRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/currencyrate")
public class CurrencyRateRestController {

    final static Logger logger = Logger.getLogger(CurrencyRateRestController.class);

    @Autowired
    private CurrencyRateService currencyRateService;

    @GetMapping({"/list"})
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> list() {
        logger.info("Call list function");
        ApiResponse<List<CurrencyRateDTO>> apiResponse = this.currencyRateService.doView();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @RequestMapping(value = "/create",
            produces = "application/json",
            method= RequestMethod.POST)
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> create(@RequestBody CurrencyRate currencyRate) {
        logger.info("Call create function");
        ApiResponse apiResponse = this.currencyRateService.doAdd(currencyRate);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/edit")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> edit(@RequestBody CurrencyRate currencyRate) {
        logger.info("Call edit function");
        ApiResponse apiResponse = this.currencyRateService.doEdit(currencyRate);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/remove")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> remove(@RequestBody List<CurrencyRate> currencyRateList) {
        logger.info("Call remove function");
        ApiResponse apiResponse = this.currencyRateService.doDelete(currencyRateList);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/preview")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> preview(@RequestBody CurrencyRateDTO currencyRateDTO) {
        logger.info("Call preview function");
        ApiResponse apiResponse = this.currencyRateService.doPreview(currencyRateDTO);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

} 
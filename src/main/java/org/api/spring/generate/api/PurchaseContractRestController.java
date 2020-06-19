package org.api.spring.generate.api;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.PurchaseContract;
import org.api.spring.generate.dto.PurchaseContractDTO;
import org.api.spring.generate.service.PurchaseContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/purchasecontract")
public class PurchaseContractRestController {

    final static Logger logger = Logger.getLogger(PurchaseContractRestController.class);

    @Autowired
    private PurchaseContractService purchaseContractService;

    @GetMapping({"/list"})
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> list() {
        logger.info("Call list function");
        ApiResponse<List<PurchaseContractDTO>> apiResponse = this.purchaseContractService.doView();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @RequestMapping(value = "/create",
            produces = "application/json",
            method= RequestMethod.POST)
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> create(@RequestBody PurchaseContract purchaseContract) {
        logger.info("Call create function");
        ApiResponse apiResponse = this.purchaseContractService.doAdd(purchaseContract);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/edit")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> edit(@RequestBody PurchaseContract purchaseContract) {
        logger.info("Call edit function");
        ApiResponse apiResponse = this.purchaseContractService.doEdit(purchaseContract);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/remove")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> remove(@RequestBody List<PurchaseContract> purchaseContractList) {
        logger.info("Call remove function");
        ApiResponse apiResponse = this.purchaseContractService.doDelete(purchaseContractList);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/preview")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> preview(@RequestBody PurchaseContractDTO purchaseContractDTO) {
        logger.info("Call preview function");
        ApiResponse apiResponse = this.purchaseContractService.doPreview(purchaseContractDTO);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

} 
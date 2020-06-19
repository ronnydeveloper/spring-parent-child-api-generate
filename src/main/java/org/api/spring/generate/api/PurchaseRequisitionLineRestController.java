package org.api.spring.generate.api;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.PurchaseRequisitionLine;
import org.api.spring.generate.dto.PurchaseRequisitionLineDTO;
import org.api.spring.generate.service.PurchaseRequisitionLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/purchaserequisitionline")
public class PurchaseRequisitionLineRestController {

    final static Logger logger = Logger.getLogger(PurchaseRequisitionLineRestController.class);

    @Autowired
    private PurchaseRequisitionLineService purchaseRequisitionLineService;

    @GetMapping({"/list"})
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> list() {
        logger.info("Call list function");
        ApiResponse<List<PurchaseRequisitionLineDTO>> apiResponse = this.purchaseRequisitionLineService.doView();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @RequestMapping(value = "/create",
            produces = "application/json",
            method= RequestMethod.POST)
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> create(@RequestBody PurchaseRequisitionLine purchaseRequisitionLine) {
        logger.info("Call create function");
        ApiResponse apiResponse = this.purchaseRequisitionLineService.doAdd(purchaseRequisitionLine);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/edit")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> edit(@RequestBody PurchaseRequisitionLine purchaseRequisitionLine) {
        logger.info("Call edit function");
        ApiResponse apiResponse = this.purchaseRequisitionLineService.doEdit(purchaseRequisitionLine);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/remove")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> remove(@RequestBody List<PurchaseRequisitionLine> purchaseRequisitionLineList) {
        logger.info("Call remove function");
        ApiResponse apiResponse = this.purchaseRequisitionLineService.doDelete(purchaseRequisitionLineList);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/preview")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> preview(@RequestBody PurchaseRequisitionLineDTO purchaseRequisitionLineDTO) {
        logger.info("Call preview function");
        ApiResponse apiResponse = this.purchaseRequisitionLineService.doPreview(purchaseRequisitionLineDTO);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

} 
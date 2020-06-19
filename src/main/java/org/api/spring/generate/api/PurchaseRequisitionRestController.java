package org.api.spring.generate.api;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.PurchaseRequisition;
import org.api.spring.generate.dto.PurchaseRequisitionDTO;
import org.api.spring.generate.service.PurchaseRequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/purchaserequisition")
public class PurchaseRequisitionRestController {

    final static Logger logger = Logger.getLogger(PurchaseRequisitionRestController.class);

    @Autowired
    private PurchaseRequisitionService purchaseRequisitionService;

    @GetMapping({"/list"})
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> list() {
        logger.info("Call list function");
        ApiResponse<List<PurchaseRequisitionDTO>> apiResponse = this.purchaseRequisitionService.doView();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @RequestMapping(value = "/create",
            produces = "application/json",
            method= RequestMethod.POST)
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> create(@RequestBody PurchaseRequisition purchaseRequisition) {
        logger.info("Call create function");
        ApiResponse apiResponse = this.purchaseRequisitionService.doAdd(purchaseRequisition);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/edit")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> edit(@RequestBody PurchaseRequisition purchaseRequisition) {
        logger.info("Call edit function");
        ApiResponse apiResponse = this.purchaseRequisitionService.doEdit(purchaseRequisition);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/remove")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> remove(@RequestBody List<PurchaseRequisition> purchaseRequisitionList) {
        logger.info("Call remove function");
        ApiResponse apiResponse = this.purchaseRequisitionService.doDelete(purchaseRequisitionList);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/preview")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> preview(@RequestBody PurchaseRequisitionDTO purchaseRequisitionDTO) {
        logger.info("Call preview function");
        ApiResponse apiResponse = this.purchaseRequisitionService.doPreview(purchaseRequisitionDTO);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

} 
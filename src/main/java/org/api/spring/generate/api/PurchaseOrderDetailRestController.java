package org.api.spring.generate.api;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.PurchaseOrderDetail;
import org.api.spring.generate.dto.PurchaseOrderDetailDTO;
import org.api.spring.generate.service.PurchaseOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/purchaseorderdetail")
public class PurchaseOrderDetailRestController {

    final static Logger logger = Logger.getLogger(PurchaseOrderDetailRestController.class);

    @Autowired
    private PurchaseOrderDetailService purchaseOrderDetailService;

    @GetMapping({"/list"})
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> list() {
        logger.info("Call list function");
        ApiResponse<List<PurchaseOrderDetailDTO>> apiResponse = this.purchaseOrderDetailService.doView();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @RequestMapping(value = "/create",
            produces = "application/json",
            method= RequestMethod.POST)
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> create(@RequestBody PurchaseOrderDetail purchaseOrderDetail) {
        logger.info("Call create function");
        ApiResponse apiResponse = this.purchaseOrderDetailService.doAdd(purchaseOrderDetail);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/edit")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> edit(@RequestBody PurchaseOrderDetail purchaseOrderDetail) {
        logger.info("Call edit function");
        ApiResponse apiResponse = this.purchaseOrderDetailService.doEdit(purchaseOrderDetail);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/remove")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> remove(@RequestBody List<PurchaseOrderDetail> purchaseOrderDetailList) {
        logger.info("Call remove function");
        ApiResponse apiResponse = this.purchaseOrderDetailService.doDelete(purchaseOrderDetailList);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/preview")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> preview(@RequestBody PurchaseOrderDetailDTO purchaseOrderDetailDTO) {
        logger.info("Call preview function");
        ApiResponse apiResponse = this.purchaseOrderDetailService.doPreview(purchaseOrderDetailDTO);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

} 
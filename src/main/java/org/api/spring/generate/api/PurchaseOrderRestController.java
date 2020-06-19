package org.api.spring.generate.api;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.PurchaseOrder;
import org.api.spring.generate.dto.PurchaseOrderDTO;
import org.api.spring.generate.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/purchaseorder")
public class PurchaseOrderRestController {

    final static Logger logger = Logger.getLogger(PurchaseOrderRestController.class);

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping({"/list"})
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> list() {
        logger.info("Call list function");
        ApiResponse<List<PurchaseOrderDTO>> apiResponse = this.purchaseOrderService.doView();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @RequestMapping(value = "/create",
            produces = "application/json",
            method= RequestMethod.POST)
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> create(@RequestBody PurchaseOrder purchaseOrder) {
        logger.info("Call create function");
        ApiResponse apiResponse = this.purchaseOrderService.doAdd(purchaseOrder);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/edit")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> edit(@RequestBody PurchaseOrder purchaseOrder) {
        logger.info("Call edit function");
        ApiResponse apiResponse = this.purchaseOrderService.doEdit(purchaseOrder);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/remove")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> remove(@RequestBody List<PurchaseOrder> purchaseOrderList) {
        logger.info("Call remove function");
        ApiResponse apiResponse = this.purchaseOrderService.doDelete(purchaseOrderList);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/preview")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> preview(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        logger.info("Call preview function");
        ApiResponse apiResponse = this.purchaseOrderService.doPreview(purchaseOrderDTO);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

} 
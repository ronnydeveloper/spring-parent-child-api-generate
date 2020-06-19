package org.api.spring.generate.api;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.PaymentTerm;
import org.api.spring.generate.dto.PaymentTermDTO;
import org.api.spring.generate.service.PaymentTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/paymentterm")
public class PaymentTermRestController {

    final static Logger logger = Logger.getLogger(PaymentTermRestController.class);

    @Autowired
    private PaymentTermService paymentTermService;

    @GetMapping({"/list"})
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> list() {
        logger.info("Call list function");
        ApiResponse<List<PaymentTermDTO>> apiResponse = this.paymentTermService.doView();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @RequestMapping(value = "/create",
            produces = "application/json",
            method= RequestMethod.POST)
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> create(@RequestBody PaymentTerm paymentTerm) {
        logger.info("Call create function");
        ApiResponse apiResponse = this.paymentTermService.doAdd(paymentTerm);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/edit")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> edit(@RequestBody PaymentTerm paymentTerm) {
        logger.info("Call edit function");
        ApiResponse apiResponse = this.paymentTermService.doEdit(paymentTerm);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/remove")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> remove(@RequestBody List<PaymentTerm> paymentTermList) {
        logger.info("Call remove function");
        ApiResponse apiResponse = this.paymentTermService.doDelete(paymentTermList);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/preview")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> preview(@RequestBody PaymentTermDTO paymentTermDTO) {
        logger.info("Call preview function");
        ApiResponse apiResponse = this.paymentTermService.doPreview(paymentTermDTO);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

} 
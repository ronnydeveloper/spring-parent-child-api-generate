package org.api.spring.generate.api;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.InvoiceReceived;
import org.api.spring.generate.dto.InvoiceReceivedDTO;
import org.api.spring.generate.service.InvoiceReceivedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/invoicereceived")
public class InvoiceReceivedRestController {

    final static Logger logger = Logger.getLogger(InvoiceReceivedRestController.class);

    @Autowired
    private InvoiceReceivedService invoiceReceivedService;

    @GetMapping({"/list"})
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> list() {
        logger.info("Call list function");
        ApiResponse<List<InvoiceReceivedDTO>> apiResponse = this.invoiceReceivedService.doView();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @RequestMapping(value = "/create",
            produces = "application/json",
            method= RequestMethod.POST)
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> create(@RequestBody InvoiceReceived invoiceReceived) {
        logger.info("Call create function");
        ApiResponse apiResponse = this.invoiceReceivedService.doAdd(invoiceReceived);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/edit")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> edit(@RequestBody InvoiceReceived invoiceReceived) {
        logger.info("Call edit function");
        ApiResponse apiResponse = this.invoiceReceivedService.doEdit(invoiceReceived);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/remove")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> remove(@RequestBody List<InvoiceReceived> invoiceReceivedList) {
        logger.info("Call remove function");
        ApiResponse apiResponse = this.invoiceReceivedService.doDelete(invoiceReceivedList);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/preview")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> preview(@RequestBody InvoiceReceivedDTO invoiceReceivedDTO) {
        logger.info("Call preview function");
        ApiResponse apiResponse = this.invoiceReceivedService.doPreview(invoiceReceivedDTO);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

} 
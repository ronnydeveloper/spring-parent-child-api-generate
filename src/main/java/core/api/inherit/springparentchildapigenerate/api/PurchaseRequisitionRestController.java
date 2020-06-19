package core.api.inherit.springparentchildapigenerate.api;

import core.api.inherit.springparentchildapigenerate.dto.PurchaseRequisitionDTO;
import core.api.inherit.springparentchildapigenerate.entity.PurchaseRequisition;
import core.api.inherit.springparentchildapigenerate.service.PurchaseRequisitionService;
import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchaserequisition")
public class PurchaseRequisitionRestController {

    final static Logger logger = Logger.getLogger(PurchaseRequisitionRestController.class);

    @Autowired
    private PurchaseRequisitionService purchaseRequisitionService;

    @GetMapping({"/list"})
    public ResponseEntity<ApiResponse> list() {
        logger.info("Call list function");
        ApiResponse<List<PurchaseRequisitionDTO>> apiResponse = this.purchaseRequisitionService.doView();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @RequestMapping(value = "/create",
            produces = "application/json",
            method= RequestMethod.POST)
    public ResponseEntity<ApiResponse> create(@RequestBody PurchaseRequisition purchaseRequisition) {
        logger.info("Call create function");
        ApiResponse apiResponse = this.purchaseRequisitionService.doAdd(purchaseRequisition);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/edit")
    public ResponseEntity<ApiResponse> edit(@RequestBody PurchaseRequisition purchaseRequisition) {
        logger.info("Call edit function");
        ApiResponse apiResponse = this.purchaseRequisitionService.doEdit(purchaseRequisition);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/remove")
    public ResponseEntity<ApiResponse> remove(@RequestBody List<PurchaseRequisition> purchaseRequisitionList) {
        logger.info("Call remove function");
        ApiResponse apiResponse = this.purchaseRequisitionService.doDelete(purchaseRequisitionList);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/preview")
    public ResponseEntity<ApiResponse> preview(@RequestBody PurchaseRequisitionDTO purchaseRequisitionDTO) {
        logger.info("Call preview function");
        ApiResponse apiResponse = this.purchaseRequisitionService.doPreview(purchaseRequisitionDTO);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

} 
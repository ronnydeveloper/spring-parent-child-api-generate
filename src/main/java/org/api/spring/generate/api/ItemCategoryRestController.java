package org.api.spring.generate.api;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.ItemCategory;
import org.api.spring.generate.dto.ItemCategoryDTO;
import org.api.spring.generate.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/itemcategory")
public class ItemCategoryRestController {

    final static Logger logger = Logger.getLogger(ItemCategoryRestController.class);

    @Autowired
    private ItemCategoryService itemCategoryService;

    @GetMapping({"/list"})
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> list() {
        logger.info("Call list function");
        ApiResponse<List<ItemCategoryDTO>> apiResponse = this.itemCategoryService.doView();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @RequestMapping(value = "/create",
            produces = "application/json",
            method= RequestMethod.POST)
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> create(@RequestBody ItemCategory itemCategory) {
        logger.info("Call create function");
        ApiResponse apiResponse = this.itemCategoryService.doAdd(itemCategory);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/edit")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> edit(@RequestBody ItemCategory itemCategory) {
        logger.info("Call edit function");
        ApiResponse apiResponse = this.itemCategoryService.doEdit(itemCategory);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/remove")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> remove(@RequestBody List<ItemCategory> itemCategoryList) {
        logger.info("Call remove function");
        ApiResponse apiResponse = this.itemCategoryService.doDelete(itemCategoryList);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/preview")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> preview(@RequestBody ItemCategoryDTO itemCategoryDTO) {
        logger.info("Call preview function");
        ApiResponse apiResponse = this.itemCategoryService.doPreview(itemCategoryDTO);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

} 
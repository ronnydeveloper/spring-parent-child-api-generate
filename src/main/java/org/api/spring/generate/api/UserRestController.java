package org.api.spring.generate.api;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.User;
import org.api.spring.generate.dto.UserDTO;
import org.api.spring.generate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    final static Logger logger = Logger.getLogger(UserRestController.class);

    @Autowired
    private UserService userService;

    @GetMapping({"/list"})
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> list() {
        logger.info("Call list function");
        ApiResponse<List<UserDTO>> apiResponse = this.userService.doView();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @RequestMapping(value = "/create",
            produces = "application/json",
            method= RequestMethod.POST)
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> create(@RequestBody User user) {
        logger.info("Call create function");
        ApiResponse apiResponse = this.userService.doAdd(user);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/edit")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> edit(@RequestBody User user) {
        logger.info("Call edit function");
        ApiResponse apiResponse = this.userService.doEdit(user);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/remove")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> remove(@RequestBody List<User> userList) {
        logger.info("Call remove function");
        ApiResponse apiResponse = this.userService.doDelete(userList);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/preview")
    @PreAuthorize("@securityConfigService.hasPermission()")
    public ResponseEntity<ApiResponse> preview(@RequestBody UserDTO userDTO) {
        logger.info("Call preview function");
        ApiResponse apiResponse = this.userService.doPreview(userDTO);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

} 
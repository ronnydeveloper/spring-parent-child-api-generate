package core.api.inherit.springparentchildapigenerate.api;

import core.api.inherit.springparentchildapigenerate.dto.AccountDTO;
import core.api.inherit.springparentchildapigenerate.entity.Account;
import core.api.inherit.springparentchildapigenerate.service.AccountService;
import core.api.inherit.springparentchildapigenerate.util.enums.ResponseCode;
import core.api.inherit.springparentchildapigenerate.util.response.ApiResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountRestController {

    final static Logger logger = Logger.getLogger(AccountRestController.class);

    @Autowired
    private AccountService accountService;

    @GetMapping({"/list"})
    public ResponseEntity<ApiResponse> list() {
        logger.info("Call list function");
        ApiResponse<List<AccountDTO>> apiResponse = this.accountService.doView();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @GetMapping({"/list/model"})
    public ResponseEntity<ApiResponse> accountList() {
        logger.info("Call accountList function");
        ApiResponse apiResponse = new ApiResponse();
        try {
            List<Account> accountList = this.accountService.findAllAccount();
            apiResponse.setData(accountList);
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @RequestMapping(value = "/create",
            produces = "application/json",
            method= RequestMethod.POST)
    public ResponseEntity<ApiResponse> create(@RequestBody Account account) {
        logger.info("Call create function");
        ApiResponse apiResponse = this.accountService.doAdd(account);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/edit")
    public ResponseEntity<ApiResponse> edit(@RequestBody Account account) {
        logger.info("Call edit function");
        ApiResponse apiResponse = this.accountService.doEdit(account);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/remove")
    public ResponseEntity<ApiResponse> remove(@RequestBody List<Account> accountList) {
        logger.info("Call remove function");
        ApiResponse apiResponse = this.accountService.doDelete(accountList);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/preview")
    public ResponseEntity<ApiResponse> preview(@RequestBody AccountDTO accountDTO) {
        logger.info("Call preview function");
        ApiResponse apiResponse = this.accountService.doPreview(accountDTO);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

} 
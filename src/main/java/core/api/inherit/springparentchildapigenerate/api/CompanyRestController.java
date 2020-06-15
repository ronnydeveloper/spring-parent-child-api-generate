package core.api.inherit.springparentchildapigenerate.api;

import core.api.inherit.springparentchildapigenerate.dto.CompanyDTO;
import core.api.inherit.springparentchildapigenerate.entity.Company;
import core.api.inherit.springparentchildapigenerate.service.CompanyService;
import core.api.inherit.springparentchildapigenerate.util.response.ApiResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyRestController {

    final static Logger logger = Logger.getLogger(CompanyRestController.class);

    @Autowired
    private CompanyService companyService;

    @GetMapping({"/list"})
    public ResponseEntity<ApiResponse> list() {
        logger.info("Call list function");
        ApiResponse<List<CompanyDTO>> apiResponse = this.companyService.doView();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @RequestMapping(value = "/create",
            produces = "application/json",
            method= RequestMethod.POST)
    public ResponseEntity<ApiResponse> create(@RequestBody Company company) {
        logger.info("Call create function");
        ApiResponse apiResponse = this.companyService.doAdd(company);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/edit")
    public ResponseEntity<ApiResponse> edit(@RequestBody Company company) {
        logger.info("Call edit function");
        ApiResponse apiResponse = this.companyService.doEdit(company);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/remove")
    public ResponseEntity<ApiResponse> remove(@RequestBody List<CompanyDTO> companyDTOList) {
        logger.info("Call remove function");
        ApiResponse apiResponse = this.companyService.doDelete(companyDTOList);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @PostMapping("/preview")
    public ResponseEntity<ApiResponse> preview(@RequestBody CompanyDTO companyDTO) {
        logger.info("Call preview function");
        ApiResponse apiResponse = this.companyService.doPreview(companyDTO);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

} 
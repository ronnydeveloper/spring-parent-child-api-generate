package core.api.inherit.springparentchildapigenerate.api;

import core.api.inherit.springparentchildapigenerate.dto.PersonDTO;
import core.api.inherit.springparentchildapigenerate.entity.Person;
import core.api.inherit.springparentchildapigenerate.repository.PersonRepo;
import core.api.inherit.springparentchildapigenerate.service.PersonService;
import core.api.inherit.springparentchildapigenerate.util.enums.ResponseCode;
import core.api.inherit.springparentchildapigenerate.util.response.ApiResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author MalkeithSingh on 11-09-2019
 */
@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    final static Logger logger = Logger.getLogger(AccountRestController.class);

    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAllDetails(@PathVariable("id") Long id) {
        logger.info("Call getAllDetails function");
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.personService.getAllDetails(id));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    @GetMapping("/{id}/siblings")
    public ResponseEntity<ApiResponse> getAllSiblings(@PathVariable("id") Long id) {
        logger.info("Call getAllSiblings function");
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.personService.getAllSiblings(id));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(apiResponse);
    }

    private Function<Person, Set<PersonDTO>> findSiblings = person -> person.getParent().getChildren().stream()
            .map(p -> PersonDTO.builder().id(p.getId()).fullName(p.getFullName()).build()).collect(Collectors.toSet());

    private Function<Person, PersonDTO> mapToPersonDTO = person -> PersonDTO.builder()
            .id(person.getId())
            .fullName(person.getFullName())
            .parent(person.getParent())
            .companyID(person.getCompanyID())
            .accountID(person.getAccountID())
            .children(person.getChildren()).build();

}
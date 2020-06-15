package core.api.inherit.springparentchildapigenerate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import core.api.inherit.springparentchildapigenerate.entity.Account;
import core.api.inherit.springparentchildapigenerate.entity.Company;
import core.api.inherit.springparentchildapigenerate.entity.Person;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * @author MalkeithSingh on 11-09-2019
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PersonDTO {

    private Long id;
    private String fullName;
    private Person parent;
    private Set<Person> children;
    private Account accountID;
    private Company companyID;

}

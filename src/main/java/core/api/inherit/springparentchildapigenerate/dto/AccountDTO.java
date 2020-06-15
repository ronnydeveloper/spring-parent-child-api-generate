package core.api.inherit.springparentchildapigenerate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import core.api.inherit.springparentchildapigenerate.entity.Account;
import core.api.inherit.springparentchildapigenerate.entity.Company;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AccountDTO {
    private Long id;
    private String code;
    private String name;
    private Account parent;
    private String currency;
    private String internalType;
    private String accountType;
    private String activeStatus;
    private Company companyID;
    private String note;
    private List<Account> children;
}

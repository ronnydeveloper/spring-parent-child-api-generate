package core.api.inherit.springparentchildapigenerate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import core.api.inherit.springparentchildapigenerate.entity.Company;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TaxDTO { 

    private Long id;

    private String taxCode;

    private String name;

    private BigDecimal taxValue;

    private String calcType;

    private Company companyID;

    private String defaultCurrency;

    private String accountCode;

    private String modulType;

    private String baseTaxFlag;


} 
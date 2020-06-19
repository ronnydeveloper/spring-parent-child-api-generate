package org.api.spring.generate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.api.spring.generate.entity.Company;

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

    private Boolean baseTaxFlag;


} 
package org.api.spring.generate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BankCashDTO { 

    private Long id;

    private String name;

    private String accountCode;

    private String currency;

    private Boolean voucherFlag;

    private Boolean pettyCashFlag;

    private String journalType;


} 
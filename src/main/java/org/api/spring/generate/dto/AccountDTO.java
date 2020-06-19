package org.api.spring.generate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.api.spring.generate.entity.Account;

import org.api.spring.generate.entity.Company;

import org.api.spring.generate.entity.Account;

import java.util.Set;


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

    private Boolean activeStatus;

    private Company companyID;

    private String note;

    private Set<Account> children;


} 
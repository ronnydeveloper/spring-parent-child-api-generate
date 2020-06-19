package org.api.spring.generate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.api.spring.generate.entity.Account;

import java.util.List;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CompanyDTO { 

    private Long id;

    private String name;

    private String coaName;

    private List<Account> accounts;


} 
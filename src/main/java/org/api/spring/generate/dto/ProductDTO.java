package org.api.spring.generate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.api.spring.generate.entity.Product;

import org.api.spring.generate.entity.Company;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductDTO { 

    private Long id;

    private String code;

    private String name;

    private Product parent;

    private Integer productCategory;

    private String productUOM;

    private Boolean activeStatus;

    private Company companyID;

    private String productDesc;

    private String incomeAccount;

    private String expenseAccount;

    private Boolean localProductFlag;


} 
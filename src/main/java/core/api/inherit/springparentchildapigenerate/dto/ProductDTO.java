package core.api.inherit.springparentchildapigenerate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import core.api.inherit.springparentchildapigenerate.entity.Company;
import core.api.inherit.springparentchildapigenerate.entity.Product;
import lombok.Builder;
import lombok.Data;


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

    private String activeStatus;

    private Company companyID;

    private String productDesc;

    private String incomeAccount;

    private String expenseAccount;

    private String localProductFlag;


} 
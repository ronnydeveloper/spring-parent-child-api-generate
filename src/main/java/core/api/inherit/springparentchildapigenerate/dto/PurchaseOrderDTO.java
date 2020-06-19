package core.api.inherit.springparentchildapigenerate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import core.api.inherit.springparentchildapigenerate.entity.Company;
import core.api.inherit.springparentchildapigenerate.entity.Department;
import core.api.inherit.springparentchildapigenerate.entity.Partner;
import core.api.inherit.springparentchildapigenerate.entity.Project;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PurchaseOrderDTO { 

    private Long id;

    private String poNumber;

    private Date orderDate;

    private Project projectID;

    private Partner partnerID;

    private BigDecimal amount;

    private BigDecimal taxAmount;

    private BigDecimal totalAmount;

    private String currency;

    private String poStatus;

    private Date bidDate;

    private Date approvedDate;

    private String paymentTerm;

    private Department departmentID;

    private Company companyID;

    private String termNote;

    private String note;


} 
package org.api.spring.generate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.api.spring.generate.entity.Project;

import org.api.spring.generate.entity.Partner;

import org.api.spring.generate.entity.Department;

import org.api.spring.generate.entity.Company;

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
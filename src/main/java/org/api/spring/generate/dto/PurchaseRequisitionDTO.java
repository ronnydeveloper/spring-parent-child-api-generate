package org.api.spring.generate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.api.spring.generate.entity.Employee;

import org.api.spring.generate.entity.PurchaseOrder;

import org.api.spring.generate.entity.Company;

import org.api.spring.generate.entity.Department;

import org.api.spring.generate.entity.Project;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PurchaseRequisitionDTO { 

    private Long id;

    private String prNumber;

    private String name;

    private Date requestDate;

    private Date scheduleDate;

    private Employee requestId;

    private String prStatus;

    private PurchaseOrder poID;

    private String inp01NoDoc;

    private Date inp01TglTerbit;

    private String inp01KadepProc;

    private String inp01Bod;

    private String inp01Atasan;

    private Company companyID;

    private String createdUserId;

    private Department departmentID;

    private String currency;

    private Boolean poDibuatFlag;

    private Project projectId;

    private BigDecimal amount;


} 
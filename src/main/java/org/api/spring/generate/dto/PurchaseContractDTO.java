package org.api.spring.generate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.api.spring.generate.entity.Partner;

import org.api.spring.generate.entity.Partner;

import org.api.spring.generate.entity.Project;

import java.util.Date;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PurchaseContractDTO { 

    private Long id;

    private String contractNumber;

    private String note;

    private Partner vendorID;

    private Date startDate;

    private Date endDate;

    private Partner customerID;

    private Project projectID;


} 
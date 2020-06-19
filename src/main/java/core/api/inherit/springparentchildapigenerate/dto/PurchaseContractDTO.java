package core.api.inherit.springparentchildapigenerate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import core.api.inherit.springparentchildapigenerate.entity.Partner;
import core.api.inherit.springparentchildapigenerate.entity.Project;
import lombok.Builder;
import lombok.Data;

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
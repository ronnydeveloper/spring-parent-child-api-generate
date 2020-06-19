package core.api.inherit.springparentchildapigenerate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import core.api.inherit.springparentchildapigenerate.entity.*;
import lombok.Builder;
import lombok.Data;

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

    private String poDibuatFlag;

    private Project projectId;

    private BigDecimal amount;


} 
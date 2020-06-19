package org.api.spring.generate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.api.spring.generate.entity.Fiscal;

import java.util.Date;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FiscalYearDTO { 

    private Long id;

    private String fiscalYear;

    private String periode;

    private Date startDate;

    private Date endDate;

    private String status;

    private Fiscal fiscalID;


} 
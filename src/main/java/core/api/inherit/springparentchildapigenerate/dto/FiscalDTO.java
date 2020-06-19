package core.api.inherit.springparentchildapigenerate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FiscalDTO { 

    private Long id;

    private String fiscalYear;

    private Date startDate;

    private Date endDate;

    private String note;


} 
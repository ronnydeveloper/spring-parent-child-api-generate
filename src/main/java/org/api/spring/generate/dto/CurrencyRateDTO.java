package org.api.spring.generate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CurrencyRateDTO { 

    private String currency;

    private Date date;

    private String companyRate;

    private BigDecimal kursJual;

    private BigDecimal kursTengah;

    private BigDecimal kursBeli;


} 
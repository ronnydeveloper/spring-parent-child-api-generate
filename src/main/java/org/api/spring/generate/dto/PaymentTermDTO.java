package org.api.spring.generate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PaymentTermDTO { 

    private Long id;

    private String paymentTermCode;

    private String name;

    private Integer jumlah;

    private String termType;

    private Boolean calendarFlag;


} 
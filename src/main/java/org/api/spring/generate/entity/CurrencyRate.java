package org.api.spring.generate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Currency_Rate")
public class CurrencyRate { 

    @Id
    @Getter
    @Setter
    @Column(name = "Currency")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private String currency;

    @Column(name = "DATE")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;

    @Column(name = "COMPANY_RATE", nullable = true, length = 10)
    @Getter
    @Setter
    private String companyRate;

    @Column(name = "KURS_JUAL", precision = 38, scale  = 2)
    @Getter
    @Setter
    private BigDecimal kursJual;

    @Column(name = "KURS_TENGAH", precision = 38, scale  = 2)
    @Getter
    @Setter
    private BigDecimal kursTengah;

    @Column(name = "KURS_BELI", precision = 38, scale  = 2)
    @Getter
    @Setter
    private BigDecimal kursBeli;


    @Override
    public String toString() {
        return "CurrencyRate{" + 
                  "currency='" + currency+ "\'" + 
                  ", date=" + date + 
                  ", companyRate='" + companyRate+ "\'" + 
                  ", kursJual=" + kursJual + 
                  ", kursTengah=" + kursTengah + 
                  ", kursBeli=" + kursBeli + 
                 '}';
    }
} 
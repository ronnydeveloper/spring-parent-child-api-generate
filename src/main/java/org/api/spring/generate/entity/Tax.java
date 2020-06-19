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

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Tax")
public class Tax { 

    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "Tax_Code", nullable = true, length = 30)
    @Getter
    @Setter
    private String taxCode;

    @Column(name = "Name", nullable = true, length = 100)
    @Getter
    @Setter
    private String name;

    @Column(name = "Tax_Value", precision = 32, scale  = 2)
    @Getter
    @Setter
    private BigDecimal taxValue;

    @Column(name = "Calc_Type", nullable = true, length = 20)
    @Getter
    @Setter
    private String calcType;

    @JoinColumn(name = "Company_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Company_Company_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Company companyID;

    @Column(name = "Default_Currency", nullable = true, length = 3)
    @Getter
    @Setter
    private String defaultCurrency;

    @Column(name = "Account_Code", nullable = true, length = 60)
    @Getter
    @Setter
    private String accountCode;

    @Column(name = "Modul_Type", nullable = true, length = 12)
    @Getter
    @Setter
    private String modulType;

    @Column(name = "Base_Tax_Flag")
    @Getter
    @Setter
    private Boolean baseTaxFlag;


    @Override
    public String toString() {
        return "Tax{" + 
                  "id=" + id + 
                  ", taxCode='" + taxCode+ "\'" + 
                  ", name='" + name+ "\'" + 
                  ", taxValue=" + taxValue + 
                  ", calcType='" + calcType+ "\'" + 
                  ", companyID=" + companyID + 
                  ", defaultCurrency='" + defaultCurrency+ "\'" + 
                  ", accountCode='" + accountCode+ "\'" + 
                  ", modulType='" + modulType+ "\'" + 
                  ", baseTaxFlag=" + baseTaxFlag + 
                 '}';
    }
} 
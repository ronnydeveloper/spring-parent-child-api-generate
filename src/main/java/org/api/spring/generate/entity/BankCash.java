package org.api.spring.generate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Bank_Cash")
public class BankCash { 

    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "Name", nullable = true, length = 200)
    @Getter
    @Setter
    private String name;

    @Column(name = "Account_Code", nullable = true, length = 60)
    @Getter
    @Setter
    private String accountCode;

    @Column(name = "Currency", nullable = true, length = 3)
    @Getter
    @Setter
    private String currency;

    @Column(name = "Voucher_Flag")
    @Getter
    @Setter
    private Boolean voucherFlag;

    @Column(name = "Petty_Cash_Flag")
    @Getter
    @Setter
    private Boolean pettyCashFlag;

    @Column(name = "Journal_Type", nullable = true, length = 30)
    @Getter
    @Setter
    private String journalType;


    @Override
    public String toString() {
        return "BankCash{" + 
                  "id=" + id + 
                  ", name='" + name+ "\'" + 
                  ", accountCode='" + accountCode+ "\'" + 
                  ", currency='" + currency+ "\'" + 
                  ", voucherFlag=" + voucherFlag + 
                  ", pettyCashFlag=" + pettyCashFlag + 
                  ", journalType='" + journalType+ "\'" + 
                 '}';
    }
} 
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
@Table(name = "Purchase_Order")
public class PurchaseOrder { 

    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "PO_NUMBER", nullable = true, length = 60)
    @Getter
    @Setter
    private String poNumber;

    @Column(name = "ORDER_DATE")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date orderDate;

    @JoinColumn(name = "PROJECT_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Project_Project_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Project projectID;

    @JoinColumn(name = "PARTNER_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Partner_Partner_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Partner partnerID;

    @Column(name = "AMOUNT", precision = 38, scale  = 2)
    @Getter
    @Setter
    private BigDecimal amount;

    @Column(name = "TAX_AMOUNT", precision = 38, scale  = 2)
    @Getter
    @Setter
    private BigDecimal taxAmount;

    @Column(name = "TOTAL_AMOUNT", precision = 38, scale  = 2)
    @Getter
    @Setter
    private BigDecimal totalAmount;

    @Column(name = "CURRENCY", nullable = true, length = 3)
    @Getter
    @Setter
    private String currency;

    @Column(name = "PO_STATUS", nullable = true, length = 20)
    @Getter
    @Setter
    private String poStatus;

    @Column(name = "BID_DATE")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date bidDate;

    @Column(name = "APPROVED_DATE")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date approvedDate;

    @Column(name = "PAYMENT_TERM", nullable = true, length = 12)
    @Getter
    @Setter
    private String paymentTerm;

    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Department_Department_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Department departmentID;

    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Company_Company_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Company companyID;

    @Column(name = "TERM_NOTE", nullable = true, length = 5000)
    @Getter
    @Setter
    private String termNote;

    @Column(name = "NOTE", nullable = true, length = 5000)
    @Getter
    @Setter
    private String note;


    @Override
    public String toString() {
        return "PurchaseOrder{" + 
                  "id=" + id + 
                  ", poNumber='" + poNumber+ "\'" + 
                  ", orderDate=" + orderDate + 
                  ", projectID=" + projectID + 
                  ", partnerID=" + partnerID + 
                  ", amount=" + amount + 
                  ", taxAmount=" + taxAmount + 
                  ", totalAmount=" + totalAmount + 
                  ", currency='" + currency+ "\'" + 
                  ", poStatus='" + poStatus+ "\'" + 
                  ", bidDate=" + bidDate + 
                  ", approvedDate=" + approvedDate + 
                  ", paymentTerm='" + paymentTerm+ "\'" + 
                  ", departmentID=" + departmentID + 
                  ", companyID=" + companyID + 
                  ", termNote='" + termNote+ "\'" + 
                  ", note='" + note+ "\'" + 
                 '}';
    }
} 
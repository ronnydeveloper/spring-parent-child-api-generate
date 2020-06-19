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
@Table(name = "Invoice_Received")
public class InvoiceReceived { 

    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @JoinColumn(name = "PARTNER_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Partner_Partner_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Partner partnerID;

    @Column(name = "INVOICE_DATE")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date invoiceDate;

    @Column(name = "INVOICE_NUMBER", nullable = true, length = 60)
    @Getter
    @Setter
    private String invoiceNumber;

    @Column(name = "AMOUNT", precision = 38, scale  = 2)
    @Getter
    @Setter
    private BigDecimal amount;

    @Column(name = "AMOUNT_TAX", precision = 38, scale  = 2)
    @Getter
    @Setter
    private BigDecimal amountTax;

    @Column(name = "AMOUNT_TOTAL", precision = 38, scale  = 2)
    @Getter
    @Setter
    private BigDecimal amountTotal;

    @Column(name = "NO_FAKTUR_PAJAK", nullable = true, length = 60)
    @Getter
    @Setter
    private String noFakturPajak;

    @Column(name = "RECEIPT_DATE")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date receiptDate;

    @Column(name = "RECEIPT_DATE_FIN")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date receiptDateFin;

    @Column(name = "RECEIPT_STATUS", nullable = true, length = 20)
    @Getter
    @Setter
    private String receiptStatus;

    @Column(name = "NOTE", nullable = true, length = 5000)
    @Getter
    @Setter
    private String note;


    @Override
    public String toString() {
        return "InvoiceReceived{" + 
                  "id=" + id + 
                  ", partnerID=" + partnerID + 
                  ", invoiceDate=" + invoiceDate + 
                  ", invoiceNumber='" + invoiceNumber+ "\'" + 
                  ", amount=" + amount + 
                  ", amountTax=" + amountTax + 
                  ", amountTotal=" + amountTotal + 
                  ", noFakturPajak='" + noFakturPajak+ "\'" + 
                  ", receiptDate=" + receiptDate + 
                  ", receiptDateFin=" + receiptDateFin + 
                  ", receiptStatus='" + receiptStatus+ "\'" + 
                  ", note='" + note+ "\'" + 
                 '}';
    }
} 
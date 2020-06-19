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
@Table(name = "Purchase_Order_Line")
public class PurchaseOrderLine { 

    @Id
    @Getter
    @Setter
    @Column(name = "LINE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long lineId;

    @JoinColumn(name = "PO_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_PurchaseOrder_PO_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private PurchaseOrder poID;

    @Column(name = "NAME", nullable = true, length = 100)
    @Getter
    @Setter
    private String name;

    @Column(name = "PRODUCT_ID")
    @Getter
    @Setter
    private Integer productId;

    @Column(name = "PRODUCT_QTY")
    @Getter
    @Setter
    private Integer productQty;

    @Column(name = "PRODUCT_UOM", nullable = true, length = 20)
    @Getter
    @Setter
    private String productUOM;

    @Column(name = "PRICE_UNIT", precision = 38, scale  = 2)
    @Getter
    @Setter
    private BigDecimal priceUnit;

    @Column(name = "AMOUNT", precision = 38, scale  = 2)
    @Getter
    @Setter
    private BigDecimal amount;

    @Column(name = "JENIS_HITUNG_PPN", nullable = true, length = 12)
    @Getter
    @Setter
    private String jenisHitungPPN;

    @Column(name = "TAX_AMOUNT", precision = 38, scale  = 2)
    @Getter
    @Setter
    private BigDecimal taxAmount;

    @Column(name = "AMOUNT_DPP", precision = 38, scale  = 2)
    @Getter
    @Setter
    private BigDecimal amountDPP;

    @Column(name = "INVOICED_FLAG")
    @Getter
    @Setter
    private Boolean invoicedFlag;

    @Column(name = "LINE_STATUS", nullable = true, length = 20)
    @Getter
    @Setter
    private String lineStatus;

    @Column(name = "TOTAL_AMOUNT", precision = 32, scale  = 2)
    @Getter
    @Setter
    private BigDecimal totalAmount;

    @Column(name = "TAX_CODE", nullable = true, length = 20)
    @Getter
    @Setter
    private String taxCode;


    @Override
    public String toString() {
        return "PurchaseOrderLine{" + 
                  "lineId=" + lineId + 
                  ", poID=" + poID + 
                  ", name='" + name+ "\'" + 
                  ", productId=" + productId + 
                  ", productQty=" + productQty + 
                  ", productUOM='" + productUOM+ "\'" + 
                  ", priceUnit=" + priceUnit + 
                  ", amount=" + amount + 
                  ", jenisHitungPPN='" + jenisHitungPPN+ "\'" + 
                  ", taxAmount=" + taxAmount + 
                  ", amountDPP=" + amountDPP + 
                  ", invoicedFlag=" + invoicedFlag + 
                  ", lineStatus='" + lineStatus+ "\'" + 
                  ", totalAmount=" + totalAmount + 
                  ", taxCode='" + taxCode+ "\'" + 
                 '}';
    }
} 
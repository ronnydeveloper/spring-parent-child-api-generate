package core.api.inherit.springparentchildapigenerate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Purchase_Requisition_Line")
public class PurchaseRequisitionLine { 

    @Id
    @Getter
    @Setter
    @Column(name = "LINE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long lineId;

    @JoinColumn(name = "REQUISITION_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_PurchaseRequisition_Requisition_ID"), unique = false, nullable = true, updatable = false )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private PurchaseRequisition requisitionID;

    @Column(name = "REQUEST_DESC", nullable = true, length = 200)
    @Getter
    @Setter
    private String requestDesc;

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

    @Column(name = "TOTAL_AMOUNT", precision = 38, scale  = 2)
    @Getter
    @Setter
    private BigDecimal totalAmount;

    @Column(name = "INVOICED_FLAG", nullable = true, length = 1)
    @Getter
    @Setter
    private String invoicedFlag;

    @Column(name = "LINE_STATUS", nullable = true, length = 20)
    @Getter
    @Setter
    private String lineStatus;


    @Override
    public String toString() {
        return "PurchaseRequisitionLine{" + 
                  "lineId=" + lineId + 
                  ", requisitionID=" + requisitionID + 
                  ", requestDesc='" + requestDesc+ "\'" + 
                  ", productId=" + productId + 
                  ", productQty=" + productQty + 
                  ", productUOM='" + productUOM+ "\'" + 
                  ", priceUnit=" + priceUnit + 
                  ", totalAmount=" + totalAmount + 
                  ", invoicedFlag='" + invoicedFlag+ "\'" + 
                  ", lineStatus='" + lineStatus+ "\'" + 
                 '}';
    }
} 
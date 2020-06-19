package core.api.inherit.springparentchildapigenerate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Purchase_Order_Detail")
public class PurchaseOrderDetail { 

    @Id
    @Getter
    @Setter
    @Column(name = "PO_DETAIL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long poDetailId;

    @JoinColumn(name = "PO_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_PurchaseOrder_PO_ID"), unique = false, nullable = false, updatable = false )
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private PurchaseOrder poID;

    @JoinColumn(name = "REQUISITION_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_PurchaseRequisition_Requisition_ID"), unique = false, nullable = false, updatable = false )
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private PurchaseRequisition requisitionID;


    @Override
    public String toString() {
        return "PurchaseOrderDetail{" + 
                  "poDetailId=" + poDetailId + 
                  ", poID=" + poID + 
                  ", requisitionID=" + requisitionID + 
                 '}';
    }
} 
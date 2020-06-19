package org.api.spring.generate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Purchase_Contract")
public class PurchaseContract { 

    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "CONTRACT_NUMBER", nullable = true, length = 100)
    @Getter
    @Setter
    private String contractNumber;

    @Column(name = "NOTE", nullable = true, length = 5000)
    @Getter
    @Setter
    private String note;

    @JoinColumn(name = "VENDOR_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Partner_Vendor_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Partner vendorID;

    @Column(name = "START_DATE")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date startDate;

    @Column(name = "END_DATE")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date endDate;

    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Partner_Customer_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Partner customerID;

    @JoinColumn(name = "PROJECT_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Project_Project_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Project projectID;


    @Override
    public String toString() {
        return "PurchaseContract{" + 
                  "id=" + id + 
                  ", contractNumber='" + contractNumber+ "\'" + 
                  ", note='" + note+ "\'" + 
                  ", vendorID=" + vendorID + 
                  ", startDate=" + startDate + 
                  ", endDate=" + endDate + 
                  ", customerID=" + customerID + 
                  ", projectID=" + projectID + 
                 '}';
    }
} 
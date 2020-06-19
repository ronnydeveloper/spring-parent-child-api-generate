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
@Table(name = "Contract")
public class Contract { 

    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "CONTRACT_NO", nullable = true, length = 80)
    @Getter
    @Setter
    private String contractNo;

    @Column(name = "CONTRACT_NAME", nullable = true, length = 200)
    @Getter
    @Setter
    private String contractName;

    @Column(name = "PERIODE_FROM")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date periodeFrom;

    @Column(name = "PERIODE_TO")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date periodeTo;

    @JoinColumn(name = "Vendor_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Partner_Vendor_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Partner vendorID;

    @JoinColumn(name = "PARTNER_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Partner_Partner_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Partner partnerID;

    @JoinColumn(name = "Project_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Project_Project_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Project projectID;


    @Override
    public String toString() {
        return "Contract{" + 
                  "id=" + id + 
                  ", contractNo='" + contractNo+ "\'" + 
                  ", contractName='" + contractName+ "\'" + 
                  ", periodeFrom=" + periodeFrom + 
                  ", periodeTo=" + periodeTo + 
                  ", vendorID=" + vendorID + 
                  ", partnerID=" + partnerID + 
                  ", projectID=" + projectID + 
                 '}';
    }
} 
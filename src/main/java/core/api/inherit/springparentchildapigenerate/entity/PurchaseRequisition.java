package core.api.inherit.springparentchildapigenerate.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Purchase_Requisition")
public class PurchaseRequisition { 

    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "PR_NUMBER", nullable = true, length = 60)
    @Getter
    @Setter
    private String prNumber;

    @Column(name = "NAME", nullable = true, length = 500)
    @Getter
    @Setter
    private String name;

    @Column(name = "REQUEST_DATE")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date requestDate;

    @Column(name = "SCHEDULE_DATE")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date scheduleDate;

    @JoinColumn(name = "REQUEST_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Employee_REQUEST_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Employee requestId;

    @Column(name = "PR_STATUS", nullable = true, length = 20)
    @Getter
    @Setter
    private String prStatus;

    @JoinColumn(name = "PO_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_PurchaseOrder_PO_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private PurchaseOrder poID;

    @Column(name = "INP01_NODOC", nullable = true, length = 60)
    @Getter
    @Setter
    private String inp01NoDoc;

    @Column(name = "INP01_TGL_TERBIT")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date inp01TglTerbit;

    @Column(name = "INP01_KADEP_PROC", nullable = true, length = 100)
    @Getter
    @Setter
    private String inp01KadepProc;

    @Column(name = "INP01_BOD", nullable = true, length = 100)
    @Getter
    @Setter
    private String inp01Bod;

    @Column(name = "INP01_ATASAN", nullable = true, length = 100)
    @Getter
    @Setter
    private String inp01Atasan;

    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Company_Company_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Company companyID;

    @Column(name = "CREATED_USER_ID", nullable = true, length = 50)
    @Getter
    @Setter
    private String createdUserId;

    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Department_Department_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Department departmentID;

    @Column(name = "CURRENCY", nullable = true, length = 3)
    @Getter
    @Setter
    private String currency;

    @Column(name = "PO_DIBUAT_FLAG", nullable = true, length = 1)
    @Getter
    @Setter
    private String poDibuatFlag;

    @JoinColumn(name = "PROJECT_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Project_Project_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Project projectId;

    @Column(name = "AMOUNT", precision = 38, scale  = 2)
    @Getter
    @Setter
    private BigDecimal amount;


    @Override
    public String toString() {
        return "PurchaseRequisition{" + 
                  "id=" + id + 
                  ", prNumber='" + prNumber+ "\'" + 
                  ", name='" + name+ "\'" + 
                  ", requestDate=" + requestDate + 
                  ", scheduleDate=" + scheduleDate + 
                  ", requestId=" + requestId + 
                  ", prStatus='" + prStatus+ "\'" + 
                  ", poID=" + poID + 
                  ", inp01NoDoc='" + inp01NoDoc+ "\'" + 
                  ", inp01TglTerbit=" + inp01TglTerbit + 
                  ", inp01KadepProc='" + inp01KadepProc+ "\'" + 
                  ", inp01Bod='" + inp01Bod+ "\'" + 
                  ", inp01Atasan='" + inp01Atasan+ "\'" + 
                  ", companyID=" + companyID + 
                  ", createdUserId='" + createdUserId+ "\'" + 
                  ", departmentID=" + departmentID + 
                  ", currency='" + currency+ "\'" + 
                  ", poDibuatFlag='" + poDibuatFlag+ "\'" + 
                  ", projectId=" + projectId + 
                  ", amount=" + amount + 
                 '}';
    }
} 
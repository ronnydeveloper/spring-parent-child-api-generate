package core.api.inherit.springparentchildapigenerate.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Fiscal_Year")
public class FiscalYear { 

    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "Fiscal_Year", nullable = true, length = 4)
    @Getter
    @Setter
    private String fiscalYear;

    @Column(name = "Periode", nullable = true, length = 6)
    @Getter
    @Setter
    private String periode;

    @Column(name = "Start_Date")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date startDate;

    @Column(name = "End_Date")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date endDate;

    @Column(name = "Status", nullable = true, length = 12)
    @Getter
    @Setter
    private String status;

    @JoinColumn(name = "Fiscal_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Fiscal_Fiscal_ID"), unique = false, nullable = false, updatable = false )
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Fiscal fiscalID;


    @Override
    public String toString() {
        return "FiscalYear{" + 
                  "id=" + id + 
                  ", fiscalYear='" + fiscalYear+ "\'" + 
                  ", periode='" + periode+ "\'" + 
                  ", startDate=" + startDate + 
                  ", endDate=" + endDate + 
                  ", status='" + status+ "\'" + 
                  ", fiscalID=" + fiscalID + 
                 '}';
    }
} 
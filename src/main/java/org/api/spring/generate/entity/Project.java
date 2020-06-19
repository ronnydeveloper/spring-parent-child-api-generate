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
@Table(name = "Project", uniqueConstraints = @UniqueConstraint(name = "UC_Project", columnNames = {"id","NO_PROJECT"}))
public class Project { 

    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "NAME", nullable = true, length = 200)
    @Getter
    @Setter
    private String name;

    @JoinColumn(name = "PARTNER_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Partner_Partner_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Partner partnerID;

    @Column(name = "NO_PROJECT", nullable = true, length = 100)
    @Getter
    @Setter
    private String noProject;

    @Column(name = "NO_KONTRAK", nullable = true, length = 100)
    @Getter
    @Setter
    private String noKontrak;

    @Column(name = "DATE_START")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateStart;

    @Column(name = "DATE_END")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateEnd;

    @Column(name = "STATUS_BA", nullable = true, length = 20)
    @Getter
    @Setter
    private String statusBA;

    @Column(name = "TGL_KONTRAK")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date tglKontrak;

    @Column(name = "TGL_PERJANJIAN_FROM")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date tglPerjanjianFrom;

    @Column(name = "TGL_PERJANJIAN_TO")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date tglPerjanjianTo;

    @Column(name = "PROJECT_STATUS", nullable = true, length = 20)
    @Getter
    @Setter
    private String projectStatus;

    @JoinColumn(name = "Parent_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Project_Parent_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Project parent;

    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Company_Company_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Company companyID;


    @Override
    public String toString() {
        return "Project{" + 
                  "id=" + id + 
                  ", name='" + name+ "\'" + 
                  ", partnerID=" + partnerID + 
                  ", noProject='" + noProject+ "\'" + 
                  ", noKontrak='" + noKontrak+ "\'" + 
                  ", dateStart=" + dateStart + 
                  ", dateEnd=" + dateEnd + 
                  ", statusBA='" + statusBA+ "\'" + 
                  ", tglKontrak=" + tglKontrak + 
                  ", tglPerjanjianFrom=" + tglPerjanjianFrom + 
                  ", tglPerjanjianTo=" + tglPerjanjianTo + 
                  ", projectStatus='" + projectStatus+ "\'" + 
                  ", parent=" + parent + 
                  ", companyID=" + companyID + 
                 '}';
    }
} 
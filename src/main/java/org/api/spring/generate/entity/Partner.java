package org.api.spring.generate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Partner", uniqueConstraints = @UniqueConstraint(name = "UC_Partner", columnNames = {"id","NPWP_NO"}))
public class Partner { 

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

    @Column(name = "TITLE", nullable = true, length = 6)
    @Getter
    @Setter
    private String title;

    @JoinColumn(name = "Parent_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Partner_Parent_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Partner parent;

    @Column(name = "TYPE", nullable = true, length = 20)
    @Getter
    @Setter
    private String type;

    @Column(name = "IS_COMPANY")
    @Getter
    @Setter
    private Boolean isCompany;

    @Column(name = "NPWP_NO", nullable = true, length = 30)
    @Getter
    @Setter
    private String npwpNo;

    @Column(name = "NPWP_ALAMAT", nullable = true, length = 200)
    @Getter
    @Setter
    private String npwpAlamat;

    @Column(name = "ADDRESS", nullable = true, length = 200)
    @Getter
    @Setter
    private String address;

    @Column(name = "STREET", nullable = true, length = 200)
    @Getter
    @Setter
    private String street;

    @Column(name = "CITY", nullable = true, length = 100)
    @Getter
    @Setter
    private String city;

    @Column(name = "PROVINCE", nullable = true, length = 100)
    @Getter
    @Setter
    private String province;

    @Column(name = "COUNTRY", nullable = true, length = 100)
    @Getter
    @Setter
    private String country;

    @Column(name = "ZIP", nullable = true, length = 20)
    @Getter
    @Setter
    private String zip;

    @Column(name = "PHONE", nullable = true, length = 60)
    @Getter
    @Setter
    private String phone;

    @Column(name = "MOBILE", nullable = true, length = 60)
    @Getter
    @Setter
    private String mobile;

    @Column(name = "FAX", nullable = true, length = 60)
    @Getter
    @Setter
    private String fax;

    @Column(name = "EMAIL", nullable = true, length = 100)
    @Getter
    @Setter
    private String email;

    @Column(name = "ACTIVE_STATUS")
    @Getter
    @Setter
    private Boolean activeStatus;

    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Company_Company_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Company companyID;

    @Column(name = "PARTNER_DESC", nullable = true, length = 5000)
    @Getter
    @Setter
    private String partnerDesc;

    @Column(name = "ACCOUNT_REC", nullable = true, length = 60)
    @Getter
    @Setter
    private String accountRec;

    @Column(name = "ACCOUNT_PAY", nullable = true, length = 60)
    @Getter
    @Setter
    private String accountPay;


    @Override
    public String toString() {
        return "Partner{" + 
                  "id=" + id + 
                  ", name='" + name+ "\'" + 
                  ", title='" + title+ "\'" + 
                  ", parent=" + parent + 
                  ", type='" + type+ "\'" + 
                  ", isCompany=" + isCompany + 
                  ", npwpNo='" + npwpNo+ "\'" + 
                  ", npwpAlamat='" + npwpAlamat+ "\'" + 
                  ", address='" + address+ "\'" + 
                  ", street='" + street+ "\'" + 
                  ", city='" + city+ "\'" + 
                  ", province='" + province+ "\'" + 
                  ", country='" + country+ "\'" + 
                  ", zip='" + zip+ "\'" + 
                  ", phone='" + phone+ "\'" + 
                  ", mobile='" + mobile+ "\'" + 
                  ", fax='" + fax+ "\'" + 
                  ", email='" + email+ "\'" + 
                  ", activeStatus=" + activeStatus + 
                  ", companyID=" + companyID + 
                  ", partnerDesc='" + partnerDesc+ "\'" + 
                  ", accountRec='" + accountRec+ "\'" + 
                  ", accountPay='" + accountPay+ "\'" + 
                 '}';
    }
} 
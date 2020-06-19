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
@Table(name = "Product")
public class Product { 

    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "Code", nullable = true, length = 60)
    @Getter
    @Setter
    private String code;

    @Column(name = "NAME", nullable = true, length = 200)
    @Getter
    @Setter
    private String name;

    @JoinColumn(name = "Parent_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Project_Parent_ID"), unique = false, nullable = true, updatable = false )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Product parent;

    @Column(name = "Product_Category")
    @Getter
    @Setter
    private Integer productCategory;

    @Column(name = "Product_UOM", nullable = true, length = 20)
    @Getter
    @Setter
    private String productUOM;

    @Column(name = "Active_Status", nullable = true, length = 1)
    @Getter
    @Setter
    private String activeStatus;

    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Company_Company_ID"), unique = false, nullable = false, updatable = false )
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Company companyID;

    @Column(name = "Product_Desc", nullable = true, length = 5000)
    @Getter
    @Setter
    private String productDesc;

    @Column(name = "Income_Account", nullable = true, length = 60)
    @Getter
    @Setter
    private String incomeAccount;

    @Column(name = "Expense_Account", nullable = true, length = 60)
    @Getter
    @Setter
    private String expenseAccount;

    @Column(name = "Local_Product_Flag", nullable = true, length = 1)
    @Getter
    @Setter
    private String localProductFlag;


    @Override
    public String toString() {
        return "Product{" + 
                  "id=" + id + 
                  ", code='" + code+ "\'" + 
                  ", name='" + name+ "\'" + 
                  ", parent=" + parent + 
                  ", productCategory=" + productCategory + 
                  ", productUOM='" + productUOM+ "\'" + 
                  ", activeStatus='" + activeStatus+ "\'" + 
                  ", companyID=" + companyID + 
                  ", productDesc='" + productDesc+ "\'" + 
                  ", incomeAccount='" + incomeAccount+ "\'" + 
                  ", expenseAccount='" + expenseAccount+ "\'" + 
                  ", localProductFlag='" + localProductFlag+ "\'" + 
                 '}';
    }
} 
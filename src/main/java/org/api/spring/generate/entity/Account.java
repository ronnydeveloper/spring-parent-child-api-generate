package org.api.spring.generate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Account", uniqueConstraints = @UniqueConstraint(name = "UC_Account", columnNames = {"id","Code"}))
public class Account { 

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

    @Column(name = "Name", nullable = true, length = 200)
    @Getter
    @Setter
    private String name;

    @JoinColumn(name = "Parent_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Account_Parent_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Account parent;

    @Column(name = "Currency", nullable = true, length = 3)
    @Getter
    @Setter
    private String currency;

    @Column(name = "Internal_Type", nullable = true, length = 20)
    @Getter
    @Setter
    private String internalType;

    @Column(name = "Account_Type", nullable = true, length = 20)
    @Getter
    @Setter
    private String accountType;

    @Column(name = "Active_Status")
    @Getter
    @Setter
    private Boolean activeStatus;

    @JoinColumn(name = "Company_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Company_Company_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Company companyID;

    @Column(name = "Note", nullable = true, length = 5000)
    @Getter
    @Setter
    private String note;

    @OneToMany(mappedBy = "parent", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Setter
    private Set<Account> children;


    @JsonIgnore
    public Set<Account> getChildren() { 
        return children;
    }

    @Override
    public String toString() {
        return "Account{" + 
                  "id=" + id + 
                  ", code='" + code+ "\'" + 
                  ", name='" + name+ "\'" + 
                  ", parent=" + parent + 
                  ", currency='" + currency+ "\'" + 
                  ", internalType='" + internalType+ "\'" + 
                  ", accountType='" + accountType+ "\'" + 
                  ", activeStatus=" + activeStatus + 
                  ", companyID=" + companyID + 
                  ", note='" + note+ "\'" + 
                  ", children=" + children + 
                 '}';
    }
} 
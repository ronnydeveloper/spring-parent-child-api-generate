package core.api.inherit.springparentchildapigenerate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author MalkeithSingh on 11-09-2019
 */
@Entity
//@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Person")
public class Person {

    @Id
    @Getter
    @Setter
    @EqualsAndHashCode.Include
    private Long id;

    @Getter
    @Setter
    private String fullName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Parent_ID", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_Person_Person_ID"))
    @Getter
    @Setter
    private Person parent;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "parent")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Setter
    private Set<Person> children;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Account_ID", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_Account_Account_ID"))
    @Setter
    private Account accountID;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Company_ID", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_Company_Company_ID"))
    @Setter
    private Company companyID;

    @JsonIgnore
    public Set<Person> getChildren() {
        return children;
    }

    @JsonIgnore
    public Account getAccountID() {
        return accountID;
    }

    @JsonIgnore
    public Company getCompanyID() {
        return companyID;
    }
}

package core.api.inherit.springparentchildapigenerate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Company")
public class Company {

    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Getter
    @Setter
    @Column(name = "Name", nullable = true, length = 200)
    private String name;

    @Getter
    @Setter
    @Column(name = "COA_Name", nullable = true, length = 200)
    private String coaName;

    @Setter
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "companyID")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Account> accounts = new HashSet<>();

    @Setter
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "companyID")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Person> persons = new HashSet<>();

    @JsonIgnore
    public Set<Account> getAccounts() {
        return accounts;
    }

    @JsonIgnore
    public Set<Person> getPersons() {
        return persons;
    }
}

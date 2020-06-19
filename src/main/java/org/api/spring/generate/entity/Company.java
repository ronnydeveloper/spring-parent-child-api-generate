package org.api.spring.generate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


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

    @Column(name = "Name", nullable = true, length = 200)
    @Getter
    @Setter
    private String name;

    @Column(name = "COA_Name", nullable = true, length = 200)
    @Getter
    @Setter
    private String coaName;

    @OneToMany(mappedBy = "companyID", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Setter
    private List<Account> accounts;


    @JsonIgnore
    public List<Account> getAccounts() { 
        return accounts;
    }

    @Override
    public String toString() {
        return "Company{" + 
                  "id=" + id + 
                  ", name='" + name+ "\'" + 
                  ", coaName='" + coaName+ "\'" + 
                  ", accounts=" + accounts + 
                 '}';
    }
} 
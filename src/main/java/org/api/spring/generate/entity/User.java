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
@Table(name = "User")
public class User { 

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

    @JoinColumn(name = "Company_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Company_Company_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Company companyID;


    @Override
    public String toString() {
        return "User{" + 
                  "id=" + id + 
                  ", name='" + name+ "\'" + 
                  ", companyID=" + companyID + 
                 '}';
    }
} 
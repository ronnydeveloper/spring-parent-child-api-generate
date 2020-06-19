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
@Table(name = "Department")
public class Department { 

    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "Code", nullable = true, length = 30)
    @Getter
    @Setter
    private String code;

    @Column(name = "Name", nullable = true, length = 60)
    @Getter
    @Setter
    private String name;

    @JoinColumn(name = "Parent_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Department_Parent_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Department parent;

    @JoinColumn(name = "Company_ID", referencedColumnName = "id", foreignKey=@ForeignKey(name = "FK_Company_Company_ID"), unique = false, nullable = true, updatable = true )
    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Company companyID;


    @Override
    public String toString() {
        return "Department{" + 
                  "id=" + id + 
                  ", code='" + code+ "\'" + 
                  ", name='" + name+ "\'" + 
                  ", parent=" + parent + 
                  ", companyID=" + companyID + 
                 '}';
    }
} 
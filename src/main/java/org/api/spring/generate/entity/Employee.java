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
@Table(name = "Employee")
public class Employee { 

    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "NIK", nullable = true, length = 60)
    @Getter
    @Setter
    private String nik;

    @Column(name = "Name", nullable = true, length = 200)
    @Getter
    @Setter
    private String name;


    @Override
    public String toString() {
        return "Employee{" + 
                  "id=" + id + 
                  ", nik='" + nik+ "\'" + 
                  ", name='" + name+ "\'" + 
                 '}';
    }
} 
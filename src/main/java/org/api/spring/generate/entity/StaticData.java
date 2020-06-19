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
@Table(name = "Static_Data")
public class StaticData { 

    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "Data_Category", nullable = true, length = 60)
    @Getter
    @Setter
    private String dataCategory;

    @Column(name = "Code", nullable = true, length = 100)
    @Getter
    @Setter
    private String code;

    @Column(name = "Name", nullable = true, length = 200)
    @Getter
    @Setter
    private String name;


    @Override
    public String toString() {
        return "StaticData{" + 
                  "id=" + id + 
                  ", dataCategory='" + dataCategory+ "\'" + 
                  ", code='" + code+ "\'" + 
                  ", name='" + name+ "\'" + 
                 '}';
    }
} 
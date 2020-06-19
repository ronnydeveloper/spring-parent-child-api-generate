package org.api.spring.generate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Fiscal")
public class Fiscal { 

    @Id
    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "Fiscal_Year", nullable = true, length = 4)
    @Getter
    @Setter
    private String fiscalYear;

    @Column(name = "Start_Date")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date startDate;

    @Column(name = "End_Date")
    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date endDate;

    @Column(name = "Note", nullable = true, length = 200)
    @Getter
    @Setter
    private String note;


    @Override
    public String toString() {
        return "Fiscal{" + 
                  "id=" + id + 
                  ", fiscalYear='" + fiscalYear+ "\'" + 
                  ", startDate=" + startDate + 
                  ", endDate=" + endDate + 
                  ", note='" + note+ "\'" + 
                 '}';
    }
} 
package org.api.spring.generate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.api.spring.generate.entity.Partner;

import org.api.spring.generate.entity.Project;

import org.api.spring.generate.entity.Company;

import java.util.Date;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProjectDTO { 

    private Long id;

    private String name;

    private Partner partnerID;

    private String noProject;

    private String noKontrak;

    private Date dateStart;

    private Date dateEnd;

    private String statusBA;

    private Date tglKontrak;

    private Date tglPerjanjianFrom;

    private Date tglPerjanjianTo;

    private String projectStatus;

    private Project parent;

    private Company companyID;


} 
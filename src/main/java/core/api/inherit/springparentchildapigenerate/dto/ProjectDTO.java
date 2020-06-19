package core.api.inherit.springparentchildapigenerate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import core.api.inherit.springparentchildapigenerate.entity.Company;
import core.api.inherit.springparentchildapigenerate.entity.Partner;
import core.api.inherit.springparentchildapigenerate.entity.Project;
import lombok.Builder;
import lombok.Data;

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
package core.api.inherit.springparentchildapigenerate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import core.api.inherit.springparentchildapigenerate.entity.Company;
import core.api.inherit.springparentchildapigenerate.entity.Department;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DepartmentDTO { 

    private Long id;

    private String code;

    private String name;

    private Department parent;

    private Company companyID;


} 
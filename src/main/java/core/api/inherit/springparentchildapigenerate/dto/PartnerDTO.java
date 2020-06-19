package core.api.inherit.springparentchildapigenerate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import core.api.inherit.springparentchildapigenerate.entity.Company;
import core.api.inherit.springparentchildapigenerate.entity.Partner;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PartnerDTO { 

    private Long id;

    private String name;

    private String title;

    private Partner parent;

    private String type;

    private String isCompany;

    private String npwpNo;

    private String npwpAlamat;

    private String address;

    private String street;

    private String city;

    private String province;

    private String country;

    private String zip;

    private String phone;

    private String mobile;

    private String fax;

    private String email;

    private String activeStatus;

    private Company companyID;

    private String partnerDesc;

    private String accountRec;

    private String accountPay;


} 
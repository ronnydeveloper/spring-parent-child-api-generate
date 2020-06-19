package core.api.inherit.springparentchildapigenerate.service;

import core.api.inherit.springparentchildapigenerate.dto.PartnerDTO;
import core.api.inherit.springparentchildapigenerate.entity.Partner;
import id.co.ptap.circleaf.core.dto.ApiResponse;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface PartnerService {

    Partner createOrUpdatePartner(Partner partner);

    void deletePartnerById(Long id) throws EntityNotFoundException;

    Partner getPartnerById(Long id) throws EntityNotFoundException;

    List<PartnerDTO> findAll();

    ApiResponse<List<PartnerDTO>> doView();

    ApiResponse doAdd(Partner partner);

    ApiResponse doEdit(Partner partner);

    ApiResponse doDelete(List<Partner> partnerList);

    ApiResponse doPreview(PartnerDTO partnerDTO);

} 
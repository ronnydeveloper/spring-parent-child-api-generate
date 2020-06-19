package core.api.inherit.springparentchildapigenerate.service;

import core.api.inherit.springparentchildapigenerate.dto.StaticDataDTO;
import core.api.inherit.springparentchildapigenerate.entity.StaticData;
import id.co.ptap.circleaf.core.dto.ApiResponse;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface StaticDataService {

    StaticData createOrUpdateStaticData(StaticData staticData);

    void deleteStaticDataById(Long id) throws EntityNotFoundException;

    StaticData getStaticDataById(Long id) throws EntityNotFoundException;

    List<StaticDataDTO> findAll();

    ApiResponse<List<StaticDataDTO>> doView();

    ApiResponse doAdd(StaticData staticData);

    ApiResponse doEdit(StaticData staticData);

    ApiResponse doDelete(List<StaticData> staticDataList);

    ApiResponse doPreview(StaticDataDTO staticDataDTO);

} 
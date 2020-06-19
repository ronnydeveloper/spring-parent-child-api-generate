package org.api.spring.generate.service;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.StaticData;
import org.api.spring.generate.dto.StaticDataDTO;

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
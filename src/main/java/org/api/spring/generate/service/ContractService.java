package org.api.spring.generate.service;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.Contract;
import org.api.spring.generate.dto.ContractDTO;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface ContractService {

    Contract createOrUpdateContract(Contract contract);

    void deleteContractById(Long id) throws EntityNotFoundException;

    Contract getContractById(Long id) throws EntityNotFoundException;

    List<ContractDTO> findAll();

    ApiResponse<List<ContractDTO>> doView();

    ApiResponse doAdd(Contract contract);

    ApiResponse doEdit(Contract contract);

    ApiResponse doDelete(List<Contract> contractList);

    ApiResponse doPreview(ContractDTO contractDTO);

} 
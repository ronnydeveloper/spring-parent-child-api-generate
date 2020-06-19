package core.api.inherit.springparentchildapigenerate.service;

import core.api.inherit.springparentchildapigenerate.dto.PaymentTermDTO;
import core.api.inherit.springparentchildapigenerate.entity.PaymentTerm;
import id.co.ptap.circleaf.core.dto.ApiResponse;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface PaymentTermService {

    PaymentTerm createOrUpdatePaymentTerm(PaymentTerm paymentTerm);

    void deletePaymentTermById(Long id) throws EntityNotFoundException;

    PaymentTerm getPaymentTermById(Long id) throws EntityNotFoundException;

    List<PaymentTermDTO> findAll();

    ApiResponse<List<PaymentTermDTO>> doView();

    ApiResponse doAdd(PaymentTerm paymentTerm);

    ApiResponse doEdit(PaymentTerm paymentTerm);

    ApiResponse doDelete(List<PaymentTerm> paymentTermList);

    ApiResponse doPreview(PaymentTermDTO paymentTermDTO);

} 
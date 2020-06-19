package core.api.inherit.springparentchildapigenerate.service;

import core.api.inherit.springparentchildapigenerate.dto.InvoiceReceivedDTO;
import core.api.inherit.springparentchildapigenerate.entity.InvoiceReceived;
import id.co.ptap.circleaf.core.dto.ApiResponse;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface InvoiceReceivedService {

    InvoiceReceived createOrUpdateInvoiceReceived(InvoiceReceived invoiceReceived);

    void deleteInvoiceReceivedById(Long id) throws EntityNotFoundException;

    InvoiceReceived getInvoiceReceivedById(Long id) throws EntityNotFoundException;

    List<InvoiceReceivedDTO> findAll();

    ApiResponse<List<InvoiceReceivedDTO>> doView();

    ApiResponse doAdd(InvoiceReceived invoiceReceived);

    ApiResponse doEdit(InvoiceReceived invoiceReceived);

    ApiResponse doDelete(List<InvoiceReceived> invoiceReceivedList);

    ApiResponse doPreview(InvoiceReceivedDTO invoiceReceivedDTO);

} 
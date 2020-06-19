package core.api.inherit.springparentchildapigenerate.service.impl;

import core.api.inherit.springparentchildapigenerate.dto.PurchaseRequisitionDTO;
import core.api.inherit.springparentchildapigenerate.entity.*;
import core.api.inherit.springparentchildapigenerate.repository.*;
import core.api.inherit.springparentchildapigenerate.service.PurchaseRequisitionService;
import id.co.ptap.circleaf.core.dto.ApiResponse;
import id.co.ptap.circleaf.core.enums.ResponseCode;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("purchaseRequisitionService")
public class PurchaseRequisitionServiceImpl implements PurchaseRequisitionService {

    static final Logger logger = Logger.getLogger(PurchaseRequisitionServiceImpl.class);

    @Autowired
    private PurchaseRequisitionRepo purchaseRequisitionRepository;

    @Autowired
    private EmployeeRepo employeeRepository;

    @Autowired
    private PurchaseOrderRepo purchaseOrderRepository;

    @Autowired
    private CompanyRepo companyRepository;

    @Autowired
    private DepartmentRepo departmentRepository;

    @Autowired
    private ProjectRepo projectRepository;

    @Override
    public PurchaseRequisition initializationAssociation(PurchaseRequisition purchaseRequisition) {
        if(purchaseRequisition.getRequestId() != null) {
            Optional<Employee> employeeOptional = employeeRepository.findById(purchaseRequisition.getRequestId().getId());
            if(employeeOptional.isPresent()) {
                purchaseRequisition.setRequestId(employeeOptional.get());
            }
            else {
                purchaseRequisition.setRequestId(null);
            }
        }
        if(purchaseRequisition.getPoID() != null) {
            Optional<PurchaseOrder> purchaseOrderOptional = purchaseOrderRepository.findById(purchaseRequisition.getPoID().getId());
            if(purchaseOrderOptional.isPresent()) {
                purchaseRequisition.setPoID(purchaseOrderOptional.get());
            }
            else {
                purchaseRequisition.setPoID(null);
            }
        }
        if(purchaseRequisition.getCompanyID() != null) {
            Optional<Company> companyOptional = companyRepository.findById(purchaseRequisition.getCompanyID().getId());
            if(companyOptional.isPresent()) {
                purchaseRequisition.setCompanyID(companyOptional.get());
            }
            else {
                purchaseRequisition.setCompanyID(null);
            }
        }
        if(purchaseRequisition.getDepartmentID() != null) {
            Optional<Department> departmentOptional = departmentRepository.findById(purchaseRequisition.getDepartmentID().getId());
            if(departmentOptional.isPresent()) {
                purchaseRequisition.setDepartmentID(departmentOptional.get());
            }
            else {
                purchaseRequisition.setDepartmentID(null);
            }
        }
        if(purchaseRequisition.getProjectId() != null) {
            Optional<Project> projectOptional = projectRepository.findById(purchaseRequisition.getProjectId().getId());
            if(projectOptional.isPresent()) {
                purchaseRequisition.setProjectId(projectOptional.get());
            }
            else {
                purchaseRequisition.setProjectId(null);
            }
        }

        return purchaseRequisition;
    }

    @Override
    public PurchaseRequisition createOrUpdatePurchaseRequisition(PurchaseRequisition purchaseRequisition) {
         Optional<PurchaseRequisition> purchaseRequisitionOptional = purchaseRequisitionRepository.findById(purchaseRequisition.getId());
         if(purchaseRequisitionOptional.isPresent())
         {
             ModelMapper modelMapper = new ModelMapper();
             PurchaseRequisition newPurchaseRequisition = modelMapper.map(purchaseRequisition, PurchaseRequisition.class);
             newPurchaseRequisition = purchaseRequisitionRepository.save(newPurchaseRequisition);
             return newPurchaseRequisition;
         } else {
             purchaseRequisition = purchaseRequisitionRepository.save(purchaseRequisition);
             return purchaseRequisition;
         }
    }

    @Override
    public void deletePurchaseRequisitionById(Long id) throws EntityNotFoundException {
         Optional<PurchaseRequisition> purchaseRequisitionOptional = purchaseRequisitionRepository.findById(id);
         if(purchaseRequisitionOptional.isPresent())
         {
            purchaseRequisitionRepository.deleteById(id);
         } else {
            throw new EntityNotFoundException("No PurchaseRequisition record exist for given id");
         }
    }

    @Override
    public PurchaseRequisition getPurchaseRequisitionById(Long id) throws EntityNotFoundException {
         Optional<PurchaseRequisition> purchaseRequisitionOptional = purchaseRequisitionRepository.findById(id);
         if(purchaseRequisitionOptional.isPresent())
         {
            return purchaseRequisitionOptional.get();
         } else {
            throw new EntityNotFoundException("No PurchaseRequisition record exist for given id");
         }
    }

    @Override
    public ApiResponse<List<PurchaseRequisitionDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<PurchaseRequisitionDTO> purchaseRequisitionList = this.findAll();
             apiResponse.setData(purchaseRequisitionList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(PurchaseRequisition purchaseRequisition) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (purchaseRequisition == null) {
                throw new NullPointerException("PurchaseRequisition cannot be null");
            }
            else {
                long max = 0;
                long count = purchaseRequisitionRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = purchaseRequisitionRepository.max();
                }
                purchaseRequisition.setId(max);
            }
            Optional<PurchaseRequisition> purchaseRequisitionOptional = purchaseRequisitionRepository.findById(purchaseRequisition.getId());
            if(purchaseRequisitionOptional.isPresent()) {
                throw new EntityExistsException("There is a PurchaseRequisition record exist for given id");
            } else {
                this.createOrUpdatePurchaseRequisition(this.initializationAssociation(purchaseRequisition));
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(PurchaseRequisition purchaseRequisition) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (purchaseRequisition == null) {
                throw new NullPointerException("purchaseRequisition cannot be null");
            }
            this.createOrUpdatePurchaseRequisition(this.initializationAssociation(purchaseRequisition));
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<PurchaseRequisition> purchaseRequisitionList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (purchaseRequisitionList.size() < 1) {
                throw new NullPointerException("purchaseRequisition cannot be null");
            }
            for (PurchaseRequisition obj : purchaseRequisitionList) {
                this.deletePurchaseRequisitionById(obj.getId());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(PurchaseRequisitionDTO purchaseRequisitionDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getPurchaseRequisitionById(purchaseRequisitionDTO.getId()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

    @Override
    public List<PurchaseRequisitionDTO> findAll() {
        List<PurchaseRequisitionDTO> newList = new ArrayList<>();
        for(PurchaseRequisition p : purchaseRequisitionRepository.findAll()) {
             PurchaseRequisitionDTO purchaserequisitionDTO = PurchaseRequisitionDTO.builder()
                     .id(p.getId())
                     .prNumber(p.getPrNumber())
                     .name(p.getName())
                     .requestDate(p.getRequestDate())
                     .scheduleDate(p.getScheduleDate())
                     .requestId(p.getRequestId())
                     .prStatus(p.getPrStatus())
                     .poID(p.getPoID())
                     .inp01NoDoc(p.getInp01NoDoc())
                     .inp01TglTerbit(p.getInp01TglTerbit())
                     .inp01KadepProc(p.getInp01KadepProc())
                     .inp01Bod(p.getInp01Bod())
                     .inp01Atasan(p.getInp01Atasan())
                     .companyID(p.getCompanyID())
                     .createdUserId(p.getCreatedUserId())
                     .departmentID(p.getDepartmentID())
                     .currency(p.getCurrency())
                     .poDibuatFlag(p.getPoDibuatFlag())
                     .projectId(p.getProjectId())
                     .amount(p.getAmount()).build();
             newList.add(purchaserequisitionDTO);
        }
        return newList;
    }

} 
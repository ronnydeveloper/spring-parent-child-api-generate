package org.api.spring.generate.service.impl;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import id.co.ptap.circleaf.core.enums.ResponseCode;
import org.api.spring.generate.entity.Partner;
import org.api.spring.generate.dto.PartnerDTO;
import org.api.spring.generate.repository.PartnerRepo;
import org.api.spring.generate.service.PartnerService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.List;
import java.util.ArrayList;

@Service("partnerService")
public class PartnerServiceImpl implements PartnerService {

    static final Logger logger = Logger.getLogger(PartnerServiceImpl.class);

    @Autowired
    private PartnerRepo partnerRepository;

    @Override
    public Partner createOrUpdatePartner(Partner partner) {
         Optional<Partner> partnerOptional = partnerRepository.findById(partner.getId());
         if(partnerOptional.isPresent())
         {
             ModelMapper modelMapper = new ModelMapper();
             Partner newPartner = modelMapper.map(partner, Partner.class);
             newPartner = partnerRepository.save(newPartner);
             return newPartner;
         } else {
             partner = partnerRepository.save(partner);
             return partner;
         }
    }

    @Override
    public void deletePartnerById(Long id) throws EntityNotFoundException {
         Optional<Partner> partnerOptional = partnerRepository.findById(id);
         if(partnerOptional.isPresent())
         {
            partnerRepository.deleteById(id);
         } else {
            throw new EntityNotFoundException("No Partner record exist for given id");
         }
    }

    @Override
    public Partner getPartnerById(Long id) throws EntityNotFoundException {
         Optional<Partner> partnerOptional = partnerRepository.findById(id);
         if(partnerOptional.isPresent())
         {
            return partnerOptional.get();
         } else {
            throw new EntityNotFoundException("No Partner record exist for given id");
         }
    }

    @Override
    public ApiResponse<List<PartnerDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<PartnerDTO> partnerList = this.findAll();
             apiResponse.setData(partnerList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(Partner partner) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (partner == null) {
                throw new NullPointerException("Partner cannot be null");
            }
            else {
                long max = 0;
                long count = partnerRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = partnerRepository.max();
                }
                partner.setId(max);
            }
            Optional<Partner> partnerOptional = partnerRepository.findById(partner.getId());
            if(partnerOptional.isPresent()) {
                throw new EntityExistsException("There is a Partner record exist for given id");
            } else {
                this.createOrUpdatePartner(partner);
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(Partner partner) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (partner == null) {
                throw new NullPointerException("partner cannot be null");
            }
            this.createOrUpdatePartner(partner);
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<Partner> partnerList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (partnerList.size() < 1) {
                throw new NullPointerException("partner cannot be null");
            }
            for (Partner obj : partnerList) {
                this.deletePartnerById(obj.getId());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(PartnerDTO partnerDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getPartnerById(partnerDTO.getId()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

    @Override
    public List<PartnerDTO> findAll() {
        List<PartnerDTO> newList = new ArrayList<>();
        for(Partner p : partnerRepository.findAll()) {
             PartnerDTO partnerDTO = PartnerDTO.builder()
                     .id(p.getId())
                     .name(p.getName())
                     .title(p.getTitle())
                     .parent(p.getParent())
                     .type(p.getType())
                     .isCompany(p.getIsCompany())
                     .npwpNo(p.getNpwpNo())
                     .npwpAlamat(p.getNpwpAlamat())
                     .address(p.getAddress())
                     .street(p.getStreet())
                     .city(p.getCity())
                     .province(p.getProvince())
                     .country(p.getCountry())
                     .zip(p.getZip())
                     .phone(p.getPhone())
                     .mobile(p.getMobile())
                     .fax(p.getFax())
                     .email(p.getEmail())
                     .activeStatus(p.getActiveStatus())
                     .companyID(p.getCompanyID())
                     .partnerDesc(p.getPartnerDesc())
                     .accountRec(p.getAccountRec())
                     .accountPay(p.getAccountPay()).build();
             newList.add(partnerDTO);
        }
        return newList;
    }

} 
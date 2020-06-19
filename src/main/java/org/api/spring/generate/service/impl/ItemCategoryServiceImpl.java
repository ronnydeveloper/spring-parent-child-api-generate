package org.api.spring.generate.service.impl;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import id.co.ptap.circleaf.core.enums.ResponseCode;
import org.api.spring.generate.entity.ItemCategory;
import org.api.spring.generate.dto.ItemCategoryDTO;
import org.api.spring.generate.repository.ItemCategoryRepo;
import org.api.spring.generate.service.ItemCategoryService;
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

@Service("itemCategoryService")
public class ItemCategoryServiceImpl implements ItemCategoryService {

    static final Logger logger = Logger.getLogger(ItemCategoryServiceImpl.class);

    @Autowired
    private ItemCategoryRepo itemCategoryRepository;

    @Override
    public ItemCategory createOrUpdateItemCategory(ItemCategory itemCategory) {
         Optional<ItemCategory> itemCategoryOptional = itemCategoryRepository.findById(itemCategory.getId());
         if(itemCategoryOptional.isPresent())
         {
             ModelMapper modelMapper = new ModelMapper();
             ItemCategory newItemCategory = modelMapper.map(itemCategory, ItemCategory.class);
             newItemCategory = itemCategoryRepository.save(newItemCategory);
             return newItemCategory;
         } else {
             itemCategory = itemCategoryRepository.save(itemCategory);
             return itemCategory;
         }
    }

    @Override
    public void deleteItemCategoryById(Long id) throws EntityNotFoundException {
         Optional<ItemCategory> itemCategoryOptional = itemCategoryRepository.findById(id);
         if(itemCategoryOptional.isPresent())
         {
            itemCategoryRepository.deleteById(id);
         } else {
            throw new EntityNotFoundException("No ItemCategory record exist for given id");
         }
    }

    @Override
    public ItemCategory getItemCategoryById(Long id) throws EntityNotFoundException {
         Optional<ItemCategory> itemCategoryOptional = itemCategoryRepository.findById(id);
         if(itemCategoryOptional.isPresent())
         {
            return itemCategoryOptional.get();
         } else {
            throw new EntityNotFoundException("No ItemCategory record exist for given id");
         }
    }

    @Override
    public ApiResponse<List<ItemCategoryDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<ItemCategoryDTO> itemCategoryList = this.findAll();
             apiResponse.setData(itemCategoryList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(ItemCategory itemCategory) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (itemCategory == null) {
                throw new NullPointerException("ItemCategory cannot be null");
            }
            else {
                long max = 0;
                long count = itemCategoryRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = itemCategoryRepository.max();
                }
                itemCategory.setId(max);
            }
            Optional<ItemCategory> itemCategoryOptional = itemCategoryRepository.findById(itemCategory.getId());
            if(itemCategoryOptional.isPresent()) {
                throw new EntityExistsException("There is a ItemCategory record exist for given id");
            } else {
                this.createOrUpdateItemCategory(itemCategory);
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(ItemCategory itemCategory) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (itemCategory == null) {
                throw new NullPointerException("itemCategory cannot be null");
            }
            this.createOrUpdateItemCategory(itemCategory);
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<ItemCategory> itemCategoryList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (itemCategoryList.size() < 1) {
                throw new NullPointerException("itemCategory cannot be null");
            }
            for (ItemCategory obj : itemCategoryList) {
                this.deleteItemCategoryById(obj.getId());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(ItemCategoryDTO itemCategoryDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getItemCategoryById(itemCategoryDTO.getId()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

    @Override
    public List<ItemCategoryDTO> findAll() {
        List<ItemCategoryDTO> newList = new ArrayList<>();
        for(ItemCategory p : itemCategoryRepository.findAll()) {
             ItemCategoryDTO itemcategoryDTO = ItemCategoryDTO.builder()
                     .id(p.getId())
                     .name(p.getName()).build();
             newList.add(itemcategoryDTO);
        }
        return newList;
    }

} 
package core.api.inherit.springparentchildapigenerate.service;

import core.api.inherit.springparentchildapigenerate.dto.ItemCategoryDTO;
import core.api.inherit.springparentchildapigenerate.entity.ItemCategory;
import id.co.ptap.circleaf.core.dto.ApiResponse;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface ItemCategoryService {

    ItemCategory createOrUpdateItemCategory(ItemCategory itemCategory);

    void deleteItemCategoryById(Long id) throws EntityNotFoundException;

    ItemCategory getItemCategoryById(Long id) throws EntityNotFoundException;

    List<ItemCategoryDTO> findAll();

    ApiResponse<List<ItemCategoryDTO>> doView();

    ApiResponse doAdd(ItemCategory itemCategory);

    ApiResponse doEdit(ItemCategory itemCategory);

    ApiResponse doDelete(List<ItemCategory> itemCategoryList);

    ApiResponse doPreview(ItemCategoryDTO itemCategoryDTO);

} 
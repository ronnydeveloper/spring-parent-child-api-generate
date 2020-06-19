package org.api.spring.generate.service;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.Product;
import org.api.spring.generate.dto.ProductDTO;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface ProductService {

    Product createOrUpdateProduct(Product product);

    void deleteProductById(Long id) throws EntityNotFoundException;

    Product getProductById(Long id) throws EntityNotFoundException;

    List<ProductDTO> findAll();

    ApiResponse<List<ProductDTO>> doView();

    ApiResponse doAdd(Product product);

    ApiResponse doEdit(Product product);

    ApiResponse doDelete(List<Product> productList);

    ApiResponse doPreview(ProductDTO productDTO);

} 
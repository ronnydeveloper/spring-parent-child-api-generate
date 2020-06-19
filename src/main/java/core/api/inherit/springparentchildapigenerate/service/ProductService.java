package core.api.inherit.springparentchildapigenerate.service;

import core.api.inherit.springparentchildapigenerate.dto.ProductDTO;
import core.api.inherit.springparentchildapigenerate.entity.Product;
import id.co.ptap.circleaf.core.dto.ApiResponse;

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
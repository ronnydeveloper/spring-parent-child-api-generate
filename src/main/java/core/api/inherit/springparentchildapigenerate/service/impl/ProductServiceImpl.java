package core.api.inherit.springparentchildapigenerate.service.impl;

import core.api.inherit.springparentchildapigenerate.dto.ProductDTO;
import core.api.inherit.springparentchildapigenerate.entity.Product;
import core.api.inherit.springparentchildapigenerate.repository.ProductRepo;
import core.api.inherit.springparentchildapigenerate.service.ProductService;
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

@Service("productService")
public class ProductServiceImpl implements ProductService {

    static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepo productRepository;

    @Override
    public Product createOrUpdateProduct(Product product) {
         Optional<Product> productOptional = productRepository.findById(product.getId());
         if(productOptional.isPresent())
         {
             ModelMapper modelMapper = new ModelMapper();
             Product newProduct = modelMapper.map(product, Product.class);
             newProduct = productRepository.save(newProduct);
             return newProduct;
         } else {
             product = productRepository.save(product);
             return product;
         }
    }

    @Override
    public void deleteProductById(Long id) throws EntityNotFoundException {
         Optional<Product> productOptional = productRepository.findById(id);
         if(productOptional.isPresent())
         {
            productRepository.deleteById(id);
         } else {
            throw new EntityNotFoundException("No Product record exist for given id");
         }
    }

    @Override
    public Product getProductById(Long id) throws EntityNotFoundException {
         Optional<Product> productOptional = productRepository.findById(id);
         if(productOptional.isPresent())
         {
            return productOptional.get();
         } else {
            throw new EntityNotFoundException("No Product record exist for given id");
         }
    }

    @Override
    public ApiResponse<List<ProductDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<ProductDTO> productList = this.findAll();
             apiResponse.setData(productList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(Product product) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (product == null) {
                throw new NullPointerException("Product cannot be null");
            }
            else {
                long max = 0;
                long count = productRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = productRepository.max();
                }
                product.setId(max);
            }
            Optional<Product> productOptional = productRepository.findById(product.getId());
            if(productOptional.isPresent()) {
                throw new EntityExistsException("There is a Product record exist for given id");
            } else {
                this.createOrUpdateProduct(product);
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(Product product) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (product == null) {
                throw new NullPointerException("product cannot be null");
            }
            this.createOrUpdateProduct(product);
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<Product> productList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (productList.size() < 1) {
                throw new NullPointerException("product cannot be null");
            }
            for (Product obj : productList) {
                this.deleteProductById(obj.getId());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(ProductDTO productDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getProductById(productDTO.getId()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

    @Override
    public List<ProductDTO> findAll() {
        List<ProductDTO> newList = new ArrayList<>();
        for(Product p : productRepository.findAll()) {
             ProductDTO productDTO = ProductDTO.builder()
                     .id(p.getId())
                     .code(p.getCode())
                     .name(p.getName())
                     .parent(p.getParent())
                     .productCategory(p.getProductCategory())
                     .productUOM(p.getProductUOM())
                     .activeStatus(p.getActiveStatus())
                     .companyID(p.getCompanyID())
                     .productDesc(p.getProductDesc())
                     .incomeAccount(p.getIncomeAccount())
                     .expenseAccount(p.getExpenseAccount())
                     .localProductFlag(p.getLocalProductFlag()).build();
             newList.add(productDTO);
        }
        return newList;
    }

} 
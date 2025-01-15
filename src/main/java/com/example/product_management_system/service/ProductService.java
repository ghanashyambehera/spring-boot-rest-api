package com.example.product_management_system.service;

import com.example.product_management_system.dto.ProductDto;
import com.example.product_management_system.dto.ProductResponse;

import java.util.List;

public interface ProductService {

   boolean saveProduct(ProductDto productDto);

   List<ProductDto> getAllProducts();

   ProductDto getProductById(Integer id);

   boolean deleteProduct(Integer id);

   ProductResponse getPproductSwitchPagination(int pageNo, int pageSize, String sortBy, String sortDir);

}

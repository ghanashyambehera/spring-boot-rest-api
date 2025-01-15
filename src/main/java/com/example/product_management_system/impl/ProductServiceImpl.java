package com.example.product_management_system.impl;

import com.example.product_management_system.dto.ProductDto;
import com.example.product_management_system.dto.ProductResponse;
import com.example.product_management_system.model.Product;
import com.example.product_management_system.repository.ProductRepository;
import com.example.product_management_system.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean saveProduct(ProductDto productDto) {
        Product product = mapper.map(productDto, Product.class);
        Product save = productRepository.save(product);
        return true;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> collect = products.stream().map(product -> mapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public ProductDto getProductById(Integer id) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()) {
            Product product = byId.get();
            ProductDto productDto = mapper.map(product, ProductDto.class);
            return productDto;
        }
        return null;

    }

    @Override
    public boolean deleteProduct(Integer id) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()) {
            Product product = byId.get();
            productRepository.delete(product);
            return true;
        }
        return false;
    }

    @Override
    public ProductResponse getPproductSwitchPagination(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort ascending = Sort.by(sortBy).ascending();

        Sort descending = Sort.by(sortBy).descending();

        Sort sort = sortDir.equalsIgnoreCase("asc") ? ascending : descending;


        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Product> page = productRepository.findAll(pageable);
        List<Product> products = page.getContent();
        List<ProductDto> productDtos = products.stream().map(prod -> mapper.map(prod, ProductDto.class)).toList();

        long totalElements = page.getTotalElements();
        int totalPages = page.getTotalPages();
        boolean first = page.isFirst();
        boolean last = page.isLast();

        ProductResponse response = new ProductResponse();
        response.setProductDtos(productDtos);
        response.setTotalElements(totalElements);
        response.setTotalPages(totalPages);
        response.setIsfirst(first);
        response.setIslLast(last);
        response.setPageNo(pageNo);
        response.setPageSize(pageSize);
        return response;
    }
}

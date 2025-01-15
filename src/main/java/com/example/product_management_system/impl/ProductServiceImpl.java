package com.example.product_management_system.impl;

import com.example.product_management_system.dto.ProductDto;
import com.example.product_management_system.model.Product;
import com.example.product_management_system.repository.ProductRepository;
import com.example.product_management_system.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
}

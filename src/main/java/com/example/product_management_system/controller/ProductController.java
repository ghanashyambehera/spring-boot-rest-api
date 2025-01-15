package com.example.product_management_system.controller;

import com.example.product_management_system.dto.ProductDto;
import com.example.product_management_system.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/saveProduct")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto productDto) {
        try {
            boolean saveProduct = productService.saveProduct(productDto);
            if (saveProduct) {
                return new ResponseEntity<>("Product save", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Product not saved", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allProducts")
    public ResponseEntity<?> getAppProduct() {
        List<ProductDto> productDtoList;
        try {
            productDtoList = productService.getAllProducts();
            if (CollectionUtils.isEmpty(productDtoList)) {
                return new ResponseEntity<>("No product data", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(productDtoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<?> getByProduct(@PathVariable Integer id) {
        ProductDto productDto;
        try {
            productDto = productService.getProductById(id);
            if (ObjectUtils.isEmpty(productDto)) {
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        boolean deleted;
        try {
             deleted = productService.deleteProduct(id);
            if (!deleted) {
                return new ResponseEntity<>("Product not deleted", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Sucessfully deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

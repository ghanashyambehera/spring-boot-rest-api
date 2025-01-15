package com.example.product_management_system.dto;

import lombok.*;

import java.util.List;


@Data
public class ProductResponse {

    private List<ProductDto> productDtos;
    private Long totalElements;
    private int totalPages;
    private boolean isfirst;
    private boolean islLast;
    private int pageNo;
    private int pageSize;

    public List<ProductDto> getProductDtos() {
        return productDtos;
    }

    public void setProductDtos(List<ProductDto> productDtos) {
        this.productDtos = productDtos;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isIsfirst() {
        return isfirst;
    }

    public void setIsfirst(boolean isfirst) {
        this.isfirst = isfirst;
    }

    public boolean isIslLast() {
        return islLast;
    }

    public void setIslLast(boolean islLast) {
        this.islLast = islLast;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

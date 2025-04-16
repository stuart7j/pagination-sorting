package com.jsservespac.paginationsorting.service;

import com.jsservespac.paginationsorting.entity.Product;
import com.jsservespac.paginationsorting.repository.ProductRepository;
import com.jsservespac.paginationsorting.spec.ProductSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Page<Product> getProducts(String category, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        Specification<Product> spec = Specification.where(ProductSpecification.hasCategory(category))
                .and(ProductSpecification.priceGreaterThanOrEq(minPrice))
                .and(ProductSpecification.priceLessThanOrEq(maxPrice));

        return repository.findAll(spec, pageable);
    }
}

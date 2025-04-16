package com.jsservespac.paginationsorting.controller;

import com.jsservespac.paginationsorting.entity.Product;
import com.jsservespac.paginationsorting.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@Slf4j
public class ProductController {


    private final ProductService service;

    @GetMapping
    public Page<Product> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {

        log.info("Received sort parameter: {}", sort);

        String[] sortParams = sort.split(";");

        Sort sortObj = Sort.by(
                Arrays.stream(sortParams)
                        .map(s -> {
                            String[] parts = s.split(",");
                            if (parts.length != 2) {
                                throw new IllegalArgumentException("Invalid sort parameter: " + s + ". Expected format: 'property,direction'");
                            }
                            try {
                                return new Sort.Order(Sort.Direction.fromString(parts[1]), parts[0]);
                            } catch (IllegalArgumentException e) {
                                throw new IllegalArgumentException("Invalid sort direction: " + parts[1] + ". Must be 'asc' or 'desc'");
                            }
                        })
                        .toList()
        );

        Pageable pageable = PageRequest.of(page, size, sortObj);
        return service.getProducts(category, minPrice, maxPrice, pageable);
    }
}

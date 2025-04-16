package com.jsservespac.paginationsorting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private BigDecimal price;
    private LocalDateTime createdAt;

    public Product() {}

    public Product(String name, String category, BigDecimal price, LocalDateTime createdAt) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.createdAt = createdAt;
    }
}

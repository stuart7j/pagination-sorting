package com.jsservespac.paginationsorting.config;

import com.jsservespac.paginationsorting.entity.Product;
import com.jsservespac.paginationsorting.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadDemoData(ProductRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Product("Laptop", "Electronics", new BigDecimal("999.99"), LocalDateTime.now()));
                repository.save(new Product("Phone", "Electronics", new BigDecimal("499.99"), LocalDateTime.now()));
                repository.save(new Product("T-Shirt", "Clothing", new BigDecimal("19.99"), LocalDateTime.now()));
                repository.save(new Product("Jeans", "Clothing", new BigDecimal("39.99"), LocalDateTime.now()));
                repository.save(new Product("Coffee Maker", "Kitchen", new BigDecimal("89.99"), LocalDateTime.now()));
            }
        };
    }
}

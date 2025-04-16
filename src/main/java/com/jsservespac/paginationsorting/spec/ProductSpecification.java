package com.jsservespac.paginationsorting.spec;

import com.jsservespac.paginationsorting.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {

    public static Specification<Product> hasCategory(String category) {
        return (root, query, cb) ->
                category != null ? cb.equal(root.get("category"), category) : cb.conjunction();
    }

    public static Specification<Product> priceGreaterThanOrEq(BigDecimal min) {
        return (root, query, cb) ->
                min != null ? cb.greaterThanOrEqualTo(root.get("price"), min) : cb.conjunction();
    }

    public static Specification<Product> priceLessThanOrEq(BigDecimal max) {
        return (root, query, cb) ->
                max != null ? cb.lessThanOrEqualTo(root.get("price"), max) : cb.conjunction();
    }
}

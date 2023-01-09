package com.devskiller.task.product;

import java.math.BigDecimal;

public class Product {

    private Long id;

    private BigDecimal price;

    public Product(Long id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

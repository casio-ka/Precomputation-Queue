package com.devskiller.task.product;

import com.devskiller.task.precomputation.PrecomputationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductSet {
    private Long id;
    private Map<Long, Product> productsById;
    private PrecomputationResult precomputationResult;
    private int importance;

    public ProductSet(Long id, List<Product> products, int importance) {
        this.id = id;
        this.productsById = products.stream().collect(Collectors.toMap(Product::getId, p -> p));
        this.importance = importance;
    }

    public Long getId() {
        return id;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(productsById.values());
    }

    public PrecomputationResult getPrecomputationResult() {
        return precomputationResult;
    }

    public boolean hasPrecomputationResult() {
        return precomputationResult != null;
    }

    public void setPrecomputationResult(PrecomputationResult result) {
        this.precomputationResult = result;
    }

    public int getImportance() {
        return importance;
    }
}

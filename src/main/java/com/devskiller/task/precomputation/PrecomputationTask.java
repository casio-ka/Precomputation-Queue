package com.devskiller.task.precomputation;

import com.devskiller.task.product.Product;
import com.devskiller.task.product.ProductSet;

import java.math.BigDecimal;

public class PrecomputationTask {

    private ProductSet productSet;
    private PrecomputationReason reason;
    private static int schedulingOrderCounter = 0;
    private final int schedulingOrder;

    public PrecomputationTask(ProductSet productSet, PrecomputationReason reason) {
        this.productSet = productSet;
        this.reason = reason;
        this.schedulingOrder = schedulingOrderCounter++;
    }

    public void compute() {
        BigDecimal lowestPrice = productSet.getProducts().get(0).getPrice();
        BigDecimal highestPrice = lowestPrice;
        for (Product product : productSet.getProducts()) {
            if (product.getPrice().compareTo(lowestPrice) < 0) {
                lowestPrice = product.getPrice();
            } else if (product.getPrice().compareTo(highestPrice) > 0) {
                highestPrice = product.getPrice();
            }
        }
        PrecomputationResult precomputationResult = new PrecomputationResult(lowestPrice, highestPrice);
        productSet.setPrecomputationResult(precomputationResult);
    }

    public ProductSet getProductSet() {
        return productSet;
    }
    public int getSchedulingOrder() {
        return schedulingOrder;
    }

    public PrecomputationReason getReason() {
        return reason;
    }



}

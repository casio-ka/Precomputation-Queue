package com.devskiller.task.precomputation;

import java.math.BigDecimal;

public class PrecomputationResult {
    public final BigDecimal lowestPrice;
    public final BigDecimal highestPrice;

    public PrecomputationResult(BigDecimal lowestPrice, BigDecimal highestPrice) {
        this.lowestPrice = lowestPrice;
        this.highestPrice = highestPrice;
    }
}

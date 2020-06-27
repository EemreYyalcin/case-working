package com.trendyol.cart.domain.calculate.discount;

import com.trendyol.cart.domain.calculate.discount.intf.DiscountCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DiscountRateCalculator implements DiscountCalculator {

    public BigDecimal calculate(BigDecimal value, BigDecimal amount) {
        return amount.multiply(value).divide(new BigDecimal("100"), RoundingMode.HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}

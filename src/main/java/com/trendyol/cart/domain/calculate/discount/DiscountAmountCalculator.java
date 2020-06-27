package com.trendyol.cart.domain.calculate.discount;

import com.trendyol.cart.domain.calculate.discount.intf.DiscountCalculator;

import java.math.BigDecimal;

public class DiscountAmountCalculator implements DiscountCalculator {

    public BigDecimal calculate(BigDecimal value, BigDecimal amount) {
        if (amount.compareTo(value) < 0) {
            return amount;
        }
        return value;
    }
}

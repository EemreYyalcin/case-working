package com.trendyol.cart.domain.calculate.discount.intf;

import java.math.BigDecimal;

public interface DiscountCalculator {

    BigDecimal calculate(BigDecimal value, BigDecimal amount);

}

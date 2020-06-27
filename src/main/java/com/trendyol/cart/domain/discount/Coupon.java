package com.trendyol.cart.domain.discount;

import com.trendyol.cart.domain.discount.enums.*;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Coupon {

    private final Long id;

    private final BigDecimal minAmount;

    private final Discount discount;

    public Coupon(Long id, BigDecimal minAmount, BigDecimal discountAmount, DiscountType discountType) {
        this.id = id;
        this.minAmount = minAmount;
        this.discount = new Discount(discountAmount, discountType);
    }

    public boolean isApplicable(BigDecimal totalAmount){
        return totalAmount.compareTo(minAmount) >= 0;
    }

}

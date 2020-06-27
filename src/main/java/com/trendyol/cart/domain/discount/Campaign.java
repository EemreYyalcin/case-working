package com.trendyol.cart.domain.discount;

import com.trendyol.cart.domain.discount.enums.DiscountType;
import com.trendyol.cart.domain.product.Category;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Campaign {

    private final Long id;

    private final int minItemRequired;

    private final Category category;

    private final Discount discount;

    public Campaign(Long id, Category category, BigDecimal amount, int minItemRequired, DiscountType discountType) {
        this.id = id;
        this.minItemRequired = minItemRequired;
        this.category = category;
        this.discount = new Discount(amount, discountType);
    }


}

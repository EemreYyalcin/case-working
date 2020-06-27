package com.trendyol.cart.domain.discount;

import com.trendyol.cart.domain.discount.enums.DiscountType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@RequiredArgsConstructor
@Getter
public class Discount {

    private final BigDecimal value;

    private final DiscountType type;

    @Override
    public int hashCode() {
        return Objects.hash(value, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discount)) return false;
        Discount discount = (Discount) o;
        return Objects.equals(value, discount.value) &&
                type == discount.type;
    }

}

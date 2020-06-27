package com.trendyol.cart.domain.discount;

import com.trendyol.cart.domain.discount.enums.DiscountType;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Objects;

import static org.junit.Assert.*;

public class DiscountTest {

    @Test
    public void constructorCreate_SetAllParameter_FieldsNotChange() {
        BigDecimal value = new BigDecimal("100.0");
        DiscountType discountType = DiscountType.RATE;

        Discount discount = new Discount(value, discountType);
        assertNotNull(discount);
        assertEquals(value, discount.getValue());
        assertEquals(discountType, discount.getType());
    }

    @Test
    public void hashCode_SetInvalidCode_ResultIsFalse() {
        BigDecimal value = new BigDecimal("100.0");
        DiscountType discountType = DiscountType.RATE;
        Discount discount = new Discount(value, discountType);
        assertNotEquals(Objects.hash(new BigDecimal("101.0"), discountType), discount.hashCode());
    }

    @Test
    public void hashCode_SetValidCode_ResultIsTrue() {
        BigDecimal value = new BigDecimal("100.0");
        DiscountType discountType = DiscountType.AMOUNT;
        Discount discount = new Discount(value, discountType);
        assertEquals(Objects.hash(value, discountType), discount.hashCode());
    }

    @Test
    public void equals_SetSameObject_ResultsIsTrue() {
        BigDecimal value = new BigDecimal("100.0");
        DiscountType discountType = DiscountType.RATE;

        Discount discount1 = new Discount(value, discountType);
        Discount discount2 = new Discount(value, discountType);
        assertEquals(discount1, discount2);
    }

    @Test
    public void equals_SetInvalidObjects_ResultsIsFalse() {
        BigDecimal value = new BigDecimal("100.0");
        DiscountType discountType = DiscountType.RATE;

        Discount discount1 = new Discount(value, discountType);
        Discount discount2 = new Discount(value, DiscountType.AMOUNT);
        assertNotEquals(discount1, discount2);
        assertNotEquals(discount1, null);
        assertNotEquals(discount1, new Object());
    }


}

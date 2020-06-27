package com.trendyol.cart.domain.discount;

import com.trendyol.cart.domain.discount.enums.DiscountType;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CouponTest {

    @Test
    public void constructorCreate_SetAllParameter_FieldsNotChange() {
        Long id = 1L;
        BigDecimal minAmount = new BigDecimal("3000.0");
        BigDecimal discountAmount = new BigDecimal("20.0");
        DiscountType type = DiscountType.RATE;
        Discount discount = new Discount(discountAmount, type);
        Coupon coupon = new Coupon(id, minAmount, discountAmount, type);

        assertNotNull(coupon);
        assertEquals(id, coupon.getId());
        assertEquals(minAmount, coupon.getMinAmount());
        assertEquals(discount, coupon.getDiscount());
    }

    @Test
    public void isApplicable_ValidAmount_ReturnedTrue() {
        Long id = 1L;
        BigDecimal minAmount = new BigDecimal("3000.0");
        BigDecimal discountAmount = new BigDecimal("20.0");
        DiscountType type = DiscountType.RATE;
        Coupon coupon = new Coupon(id, minAmount, discountAmount, type);
        assertFalse(coupon.isApplicable(new BigDecimal("2000.0")));
        assertTrue(coupon.isApplicable(new BigDecimal("4000.0")));
    }


}

package com.trendyol.cart.domain.discount;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TotalDiscountTest {

    @Test
    public void constructorCreate_SetAllParameter_FieldsNotChange() {
        TotalDiscount totalDiscount = new TotalDiscount();
        assertEquals(BigDecimal.ZERO, totalDiscount.getCampaignDiscount());
        assertEquals(BigDecimal.ZERO, totalDiscount.getCouponDiscount());
        assertEquals(BigDecimal.ZERO, totalDiscount.getTotalDiscount());

    }

    @Test
    public void addCampaignDiscount_AddPositiveValue_ValueAdded() {
        TotalDiscount totalDiscount = new TotalDiscount();
        totalDiscount.addCampaignDiscount(new BigDecimal("100.0"));
        assertEquals(new BigDecimal("100.0"), totalDiscount.getCampaignDiscount());
    }

    @Test
    public void addCampaignDiscount_AddNegativeOrZeroValue_ValueNotChanged() {
        TotalDiscount totalDiscount = new TotalDiscount();
        totalDiscount.addCampaignDiscount(new BigDecimal("-100.0"));
        assertEquals(BigDecimal.ZERO, totalDiscount.getCampaignDiscount());
    }


    @Test
    public void addCouponDiscount_AddPositiveValue_ValueAdded() {
        TotalDiscount totalDiscount = new TotalDiscount();
        totalDiscount.addCouponDiscount(new BigDecimal("100.0"));
        assertEquals(new BigDecimal("100.0"), totalDiscount.getCouponDiscount());
    }

    @Test
    public void addCouponDiscount_AddNegativeOrZeroValue_ValueNotChanged() {
        TotalDiscount totalDiscount = new TotalDiscount();
        totalDiscount.addCouponDiscount(new BigDecimal("-100.0"));
        assertEquals(BigDecimal.ZERO, totalDiscount.getCouponDiscount());
    }

}

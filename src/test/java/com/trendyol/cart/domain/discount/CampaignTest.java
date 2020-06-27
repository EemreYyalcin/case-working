package com.trendyol.cart.domain.discount;

import com.trendyol.cart.domain.discount.enums.DiscountType;
import com.trendyol.cart.domain.product.Category;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CampaignTest {

    @Test
    public void constructorCreate_SetAllParameter_FieldsNotChange() {
        Long id = 1L;
        int minItemRequired = 5;
        Category testCategory = new Category(1L, "test");
        BigDecimal amount = new BigDecimal("100.0");
        DiscountType discountType = DiscountType.RATE;
        Discount discount = new Discount(amount, discountType);
        Campaign campaign = new Campaign(id, testCategory, amount, minItemRequired, discountType);

        assertNotNull(campaign);
        assertEquals(id, campaign.getId());
        assertEquals(minItemRequired, campaign.getMinItemRequired());
        assertEquals(testCategory, campaign.getCategory());
        assertEquals(discount, campaign.getDiscount());
    }

}

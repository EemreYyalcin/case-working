package com.trendyol.cart.domain.discount;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
public class TotalDiscount {

    private BigDecimal campaignDiscount = BigDecimal.ZERO;

    private BigDecimal couponDiscount = BigDecimal.ZERO;

    public void addCampaignDiscount(BigDecimal campaignDiscount) {
        this.campaignDiscount = this.campaignDiscount.add(campaignDiscount.max(new BigDecimal("0")));
    }

    public void addCouponDiscount(BigDecimal couponDiscount) {
        this.couponDiscount = this.couponDiscount.add(couponDiscount.max(new BigDecimal("0")));
    }


    public BigDecimal getTotalDiscount() {
        return campaignDiscount.add(couponDiscount);
    }


}

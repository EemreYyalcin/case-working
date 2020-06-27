package com.trendyol.cart.domain.calculate.shopping;

import com.trendyol.cart.domain.calculate.discount.DiscountAmountCalculator;
import com.trendyol.cart.domain.calculate.discount.DiscountRateCalculator;
import com.trendyol.cart.domain.calculate.discount.intf.DiscountCalculator;
import com.trendyol.cart.domain.discount.Campaign;
import com.trendyol.cart.domain.discount.Coupon;
import com.trendyol.cart.domain.discount.enums.*;
import com.trendyol.cart.domain.product.Category;
import com.trendyol.cart.domain.shopping.ShoppingCart;
import com.trendyol.cart.domain.shopping.CartItem;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ShoppingCartCalculator {

    public static void applyCampaigns(ShoppingCart shoppingCart, Campaign... campaigns) {
        if (campaigns == null || campaigns.length == 0) {
            return;
        }

        Set<Category> allProductCategories = shoppingCart.getItems().stream()
                .map(item -> item.getProduct().getCategory())
                .flatMap(category -> category.getAllParentCategory(new ArrayList<>()).stream())
                .collect(Collectors.toSet());

        List<Campaign> discountCampaigns = Arrays.stream(campaigns)
                .filter(campaign -> allProductCategories.contains(campaign.getCategory()))
                .filter(shoppingCart::isApplicableCampaign)
                .collect(Collectors.toList());

        Optional<BigDecimal> maxDiscount = getAvailableCampaigns(discountCampaigns).stream()
                .map(campaignsGroup -> calculateCampaignTotalDiscount(shoppingCart, campaignsGroup))
                .max(Comparator.naturalOrder());

        if (!maxDiscount.isPresent()) {
            return;
        }
        shoppingCart.addCampaignDiscount(maxDiscount.get());
    }

    public static void applyCoupon(ShoppingCart shoppingCart, Coupon coupon){
        BigDecimal totalAmountAfterCampaignDiscount = shoppingCart.getTotalAmountAfterCampaignDiscount();
        if (!coupon.isApplicable(totalAmountAfterCampaignDiscount)){
            return;
        }
        DiscountCalculator discountCalculator = coupon.getDiscount().getType() == DiscountType.RATE ? new DiscountRateCalculator() : new DiscountAmountCalculator();
        shoppingCart.addCouponDiscount(discountCalculator.calculate(coupon.getDiscount().getValue(), totalAmountAfterCampaignDiscount));
    }

    public static List<List<Campaign>> getAvailableCampaigns(List<Campaign> campaigns) {
        List<List<Campaign>> combinationCampaignList = new ArrayList<>();
        double possibleSubChild = Math.pow(2, campaigns.size());
        for (long i = 1; i < possibleSubChild; i++) {
            List<Campaign> list = new ArrayList<>();
            for (int j = 0; j < campaigns.size(); j++) {
                if ((i & (long) Math.pow(2, j)) > 0) {
                    list.add(campaigns.get(j));
                }
            }
            if (isCampaignAvailable(list)) {
                combinationCampaignList.add(list);
            }
        }
        return combinationCampaignList;
    }

    public static boolean isCampaignAvailable(List<Campaign> campaigns) {
        if (campaigns.size() == 1) {
            return true;
        }
        for (int i = 0; i < campaigns.size() - 1; ++i) {
            Campaign campaign = campaigns.get(i);
            for (int j = i; j < campaigns.size(); ++j) {
                Campaign otherCampaign = campaigns.get(j);
                if (campaign.getCategory().isParentCategory(otherCampaign.getCategory()) ||
                        otherCampaign.getCategory().isParentCategory(campaign.getCategory())) {
                    return false;
                }
            }
        }
        return true;
    }

    private static BigDecimal calculateCampaignTotalDiscount(ShoppingCart shoppingCart, List<Campaign> campaigns) {
        BigDecimal totalDiscount = BigDecimal.ZERO;
        for (Campaign campaign : campaigns) {
            DiscountCalculator discountCalculator = campaign.getDiscount().getType() == DiscountType.RATE ? new DiscountRateCalculator() : new DiscountAmountCalculator();
            totalDiscount = totalDiscount.add(
                    shoppingCart.getItems().stream()
                            .filter(cartItem -> cartItem.getProduct().getCategory().equals(campaign.getCategory()) ||
                                    cartItem.getProduct().getCategory().isParentCategory(campaign.getCategory()))
                            .map(CartItem::getTotalAmount)
                            .map(totalAmount -> discountCalculator.calculate(campaign.getDiscount().getValue(), totalAmount))
                            .reduce(BigDecimal.ZERO, BigDecimal::add));
        }
        return totalDiscount;
    }


}

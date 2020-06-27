package com.trendyol.cart.domain.shopping;

import com.trendyol.cart.domain.calculate.delivery.DeliveryCalculator;
import com.trendyol.cart.domain.calculate.shopping.ShoppingCartCalculator;
import com.trendyol.cart.domain.constants.Constants;
import com.trendyol.cart.domain.discount.Campaign;
import com.trendyol.cart.domain.discount.Coupon;
import com.trendyol.cart.domain.discount.TotalDiscount;
import com.trendyol.cart.domain.product.Product;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCart {

    @Getter
    private final List<CartItem> items = new ArrayList<>();

    private final TotalDiscount totalDiscount = new TotalDiscount();


    public void addCampaignDiscount(BigDecimal campaignDiscount) {
        totalDiscount.addCampaignDiscount(campaignDiscount);
    }

    public void addCouponDiscount(BigDecimal couponDiscount) {
        totalDiscount.addCouponDiscount(couponDiscount);
    }

    public BigDecimal getCampaignDiscount() {
        return totalDiscount.getCampaignDiscount();
    }

    public BigDecimal getCouponDiscount() {
        return totalDiscount.getCouponDiscount();
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount.getTotalDiscount();
    }

    public BigDecimal getTotalAmountBeforeDiscount() {
        return items.stream().map(CartItem::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalAmountAfterDiscount() {
        return getTotalAmountBeforeDiscount().subtract(getCampaignDiscount()).subtract(getCouponDiscount()).max(BigDecimal.ZERO);
    }

    public BigDecimal getTotalAmountAfterCampaignDiscount() {
        return getTotalAmountBeforeDiscount().subtract(getCampaignDiscount()).max(BigDecimal.ZERO);
    }

    public void applyDiscounts(Campaign... campaigns) {
        ShoppingCartCalculator.applyCampaigns(this, campaigns);
    }

    public void applyCoupon(Coupon coupon) {
        ShoppingCartCalculator.applyCoupon(this, coupon);
    }

    public BigDecimal getDeliveryCost() {
        DeliveryCalculator deliveryCalculator = new DeliveryCalculator(Constants.FIXED_COST, Constants.PER_DELIVERY, Constants.PER_PRODUCT);
        return deliveryCalculator.calculateDeliveryCost(this);
    }

    public boolean isApplicableCampaign(Campaign campaign) {
        int totalCount = items.stream()
                .filter(item -> item.getProduct().getCategory().getAllParentCategory(new ArrayList<>()).contains(campaign.getCategory()))
                .map(item -> item.getQuantity().get())
                .reduce(0, Integer::sum);
        return totalCount >= campaign.getMinItemRequired();
    }

    public void addItem(Product product, int quantity) {
        Optional<CartItem> foundedItemOptional = items.stream()
                .filter(item -> item.getProduct().equals(product))
                .findFirst();

        if (foundedItemOptional.isPresent()) {
            foundedItemOptional.get().addQuantity(quantity);
            return;
        }
        items.add(new CartItem(product, quantity));
    }

    public void print() {
        System.out.println(toString());
    }


    @Override
    public String toString() {
        return "CART\t\n" +
                "Items\t\n" + items + "\t\n\t\n\t\n" +
                "Values\t\n" +
                "Total Amount Before Discount:" + getTotalAmountBeforeDiscount() + "\t\n" +
                "Total Campaign Discount:" + getCampaignDiscount() + "\t\n" +
                "Total Amount After Campaign Discount:" + getTotalAmountAfterCampaignDiscount() + "\t\n" +
                "Total Coupon Discount: " + getCouponDiscount() + "\t\n" +
                "TotalAmount After Discount:" + getTotalAmountAfterDiscount() + "\t\n" +
                "Total Discount: " + getTotalDiscount() + "\t\n" +
                "Delivery Cost: " + getDeliveryCost() + "\t\n";
    }
}

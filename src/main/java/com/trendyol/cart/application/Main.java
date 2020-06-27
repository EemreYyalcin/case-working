package com.trendyol.cart.application;

import com.trendyol.cart.domain.discount.Campaign;
import com.trendyol.cart.domain.discount.Coupon;
import com.trendyol.cart.domain.discount.enums.DiscountType;
import com.trendyol.cart.domain.product.Category;
import com.trendyol.cart.domain.product.Product;
import com.trendyol.cart.domain.shopping.ShoppingCart;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        Category dress = new Category(1L, "Dress");
        Category man = new Category(2L, "Man");
        Category woman = new Category(3L, "Woman");
        Category pants = new Category(4L, "Pants");
        Category tShirt = new Category(5L, "T-Shirt");
        Category jewelry = new Category(6L, "Jewelry");
        Category skirt = new Category(7L, "Skirt");

        man.setParentCategory(dress);
        woman.setParentCategory(dress);
        pants.setParentCategory(man);
        tShirt.setParentCategory(man);
        jewelry.setParentCategory(woman);
        skirt.setParentCategory(woman);


        Product tShirtProduct = new Product(1L, "tShirtProduct", new BigDecimal("100.0"), tShirt);
        Product pantProduct = new Product(2L, "pantProduct", new BigDecimal("200.0"), pants);
        Product pantProduct1 = new Product(3L, "pantProduct1", new BigDecimal("300.0"), pants);
        Product jewelryProduct = new Product(4L, "jewelryProduct", new BigDecimal("200.0"), jewelry);
        Product skirtProduct = new Product(5L, "skirtProduct", new BigDecimal("100.0"), skirt);


        Campaign dressCampaign = new Campaign(1L, dress, new BigDecimal("10.0"), 5, DiscountType.RATE);
        Campaign manCampaign = new Campaign(2L, man, new BigDecimal("30.0"), 10, DiscountType.RATE);
        Campaign tShirtCampaign = new Campaign(3L, tShirt, new BigDecimal("20.0"), 5, DiscountType.RATE);
        Campaign pantCampaign = new Campaign(4L, pants, new BigDecimal("200.0"), 10, DiscountType.AMOUNT);
        Campaign womanCampaign = new Campaign(5L, woman, new BigDecimal("20.0"), 20, DiscountType.RATE);
        Campaign jewelryCampaign = new Campaign(6L, jewelry, new BigDecimal("100.0"), 10, DiscountType.AMOUNT);
        Campaign skirtCampaign = new Campaign(7L, skirt, new BigDecimal("200.0"), 5, DiscountType.AMOUNT);

        Coupon coupon = new Coupon(1L, new BigDecimal("3000.0"), new BigDecimal("20.0"), DiscountType.RATE);


        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(tShirtProduct, 10);
        shoppingCart.addItem(pantProduct, 20);
        shoppingCart.addItem(pantProduct1, 20);
        shoppingCart.addItem(jewelryProduct, 15);
        shoppingCart.addItem(skirtProduct, 10);

        shoppingCart.applyDiscounts(dressCampaign, manCampaign, tShirtCampaign, pantCampaign, womanCampaign,
                jewelryCampaign, skirtCampaign);

        shoppingCart.applyCoupon(coupon);
        shoppingCart.print();

    }


}

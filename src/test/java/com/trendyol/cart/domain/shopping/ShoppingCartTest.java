package com.trendyol.cart.domain.shopping;

import com.trendyol.cart.domain.discount.Campaign;
import com.trendyol.cart.domain.discount.Coupon;
import com.trendyol.cart.domain.discount.TotalDiscount;
import com.trendyol.cart.domain.discount.enums.DiscountType;
import com.trendyol.cart.domain.product.Category;
import com.trendyol.cart.domain.product.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class ShoppingCartTest {

    Category dress = new Category(1L, "Dress");
    Category man = new Category(2L, "Man");
    Category woman = new Category(3L, "Woman");
    Category pants = new Category(4L, "Pants");
    Category tShirt = new Category(5L, "T-Shirt");
    Category jewelry = new Category(6L, "Jewelry");
    Category skirt = new Category(7L, "Skirt");


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


    @Test
    public void constructorCreate_SetAllParameter_FieldsNotChange() {
        TotalDiscount totalDiscount = new TotalDiscount();

        ShoppingCart shoppingCart = new ShoppingCart();

        assertNotNull(shoppingCart);
        assertEquals(new ArrayList<>().size(), shoppingCart.getItems().size());
        assertEquals(totalDiscount.getCampaignDiscount(), shoppingCart.getCampaignDiscount());
        assertEquals(totalDiscount.getCouponDiscount(), shoppingCart.getCouponDiscount());
    }

    @Test
    public void addCampaignDiscount_AddPositiveValue_ValueAdded() {
        BigDecimal campaignDiscount = new BigDecimal("100.0");
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addCampaignDiscount(campaignDiscount);

        assertEquals(campaignDiscount, shoppingCart.getCampaignDiscount());
    }

    @Test
    public void addCampaignDiscount_AddNegativeOrZeroValue_ValueNotChanged() {
        BigDecimal campaignDiscount = new BigDecimal("-100.0");
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addCampaignDiscount(campaignDiscount);

        assertEquals(BigDecimal.ZERO, shoppingCart.getCampaignDiscount());
    }


    @Test
    public void addCouponDiscount_AddPositiveValue_ValueAdded() {
        BigDecimal couponDiscount = new BigDecimal("100.0");
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addCouponDiscount(couponDiscount);

        assertEquals(couponDiscount, shoppingCart.getCouponDiscount());
    }

    @Test
    public void addCouponDiscount_AddNegativeOrZeroValue_ValueNotChanged() {
        BigDecimal couponDiscount = new BigDecimal("-100.0");
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addCouponDiscount(couponDiscount);

        assertEquals(BigDecimal.ZERO, shoppingCart.getCouponDiscount());
    }

    @Test
    public void getTotalDiscount_AddValidCouponAndCampaignValue_ValidResult() {
        BigDecimal campaignDiscount = new BigDecimal("100.0");
        BigDecimal couponDiscount = new BigDecimal("200.0");
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addCampaignDiscount(campaignDiscount);
        shoppingCart.addCouponDiscount(couponDiscount);

        assertEquals(campaignDiscount.add(couponDiscount), shoppingCart.getTotalDiscount());

    }

    @Test
    public void getTotalAmountAfterCampaignDiscount_AddValidCampaigns_ValidResult() {
        man.setParentCategory(dress);
        woman.setParentCategory(dress);
        pants.setParentCategory(man);
        tShirt.setParentCategory(man);
        jewelry.setParentCategory(woman);
        skirt.setParentCategory(woman);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(tShirtProduct, 10);
        shoppingCart.addItem(pantProduct, 20);
        shoppingCart.addItem(pantProduct1, 20);
        shoppingCart.addItem(jewelryProduct, 15);
        shoppingCart.addItem(skirtProduct, 10);

        shoppingCart.applyDiscounts(dressCampaign, manCampaign, tShirtCampaign, pantCampaign, womanCampaign,
                jewelryCampaign, skirtCampaign);

        Coupon coupon = new Coupon(1L, new BigDecimal("3000.0"), new BigDecimal("20.0"), DiscountType.RATE);
        shoppingCart.applyCoupon(coupon);

        assertEquals(new BigDecimal("15000.0"), shoppingCart.getTotalAmountBeforeDiscount());
        assertEquals(new BigDecimal("10900.00"), shoppingCart.getTotalAmountAfterCampaignDiscount());
        assertEquals(new BigDecimal("8720.00"), shoppingCart.getTotalAmountAfterDiscount());
        assertEquals(new BigDecimal("6280.00"), shoppingCart.getTotalDiscount());
        assertEquals(new BigDecimal("2180.00"), shoppingCart.getCouponDiscount());
        assertEquals(new BigDecimal("4100.00"), shoppingCart.getCampaignDiscount());
        assertEquals(new BigDecimal("63.45"), shoppingCart.getDeliveryCost());

    }

    @Test
    public void applyCoupon_AddCouponValues_ValidResult() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(tShirtProduct, 10);

        Coupon coupon = new Coupon(1L, new BigDecimal("1001.0"), new BigDecimal("20.0"), DiscountType.RATE);
        Coupon coupon1 = new Coupon(2L, new BigDecimal("999.0"), new BigDecimal("20.0"), DiscountType.RATE);
        shoppingCart.applyCoupon(coupon);

        assertEquals(BigDecimal.ZERO, shoppingCart.getCouponDiscount());
        shoppingCart.applyCoupon(coupon1);
        assertNotEquals(BigDecimal.ZERO, shoppingCart.getCouponDiscount());

    }

    @Test
    public void applyCampaign_AddInvalidCampaign_InValidResult() {
        Campaign tShirtCampaign = new Campaign(7L, tShirt, new BigDecimal("1001.0"), 5, DiscountType.AMOUNT);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(tShirtProduct, 10);

       shoppingCart.applyDiscounts();
       assertEquals(BigDecimal.ZERO, shoppingCart.getCampaignDiscount());

       shoppingCart.applyDiscounts(tShirtCampaign);
       assertEquals(new BigDecimal("1000.0"), shoppingCart.getCampaignDiscount());

    }

    @Test
    public void isApplicableCampaign_AddValidCampaign_ValidResult() {
        man.setParentCategory(dress);
        woman.setParentCategory(dress);
        pants.setParentCategory(man);
        tShirt.setParentCategory(man);
        jewelry.setParentCategory(woman);
        skirt.setParentCategory(woman);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(tShirtProduct, 10);
        shoppingCart.addItem(pantProduct, 20);
        shoppingCart.addItem(pantProduct1, 20);

        assertTrue(shoppingCart.isApplicableCampaign(tShirtCampaign));
        assertTrue(shoppingCart.isApplicableCampaign(pantCampaign));
        assertTrue(shoppingCart.isApplicableCampaign(dressCampaign));
        assertFalse(shoppingCart.isApplicableCampaign(jewelryCampaign));
        assertFalse(shoppingCart.isApplicableCampaign(womanCampaign));
        assertFalse(shoppingCart.isApplicableCampaign(skirtCampaign));
    }


    @Test
    public void addItem_AddValidProduct_ValidResult() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(tShirtProduct, 10);
        shoppingCart.addItem(pantProduct, 20);
        shoppingCart.addItem(pantProduct1, 20);

        assertEquals(3, shoppingCart.getItems().size());
        assertEquals(new Integer(50), shoppingCart.getItems().stream().map(CartItem::getQuantity).map(AtomicInteger::get).reduce(0, Integer::sum));
    }

    @Test
    public void addItem_AddValidSameProduct_ResultChangeProductQuantity() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(tShirtProduct, 10);
        shoppingCart.addItem(pantProduct, 20);
        shoppingCart.addItem(pantProduct, 10);

        assertEquals(2, shoppingCart.getItems().size());
        assertEquals(new Integer(40), shoppingCart.getItems().stream().map(CartItem::getQuantity).map(AtomicInteger::get).reduce(0, Integer::sum));
    }


    @Test
    public void toString_SetValidObjects_ResultsValidString() {

        man.setParentCategory(dress);
        woman.setParentCategory(dress);
        pants.setParentCategory(man);
        tShirt.setParentCategory(man);
        jewelry.setParentCategory(woman);
        skirt.setParentCategory(woman);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(tShirtProduct, 10);
        shoppingCart.addItem(pantProduct, 20);
        shoppingCart.addItem(pantProduct1, 20);
        shoppingCart.addItem(jewelryProduct, 15);
        shoppingCart.addItem(skirtProduct, 10);

        shoppingCart.applyDiscounts(dressCampaign, manCampaign, tShirtCampaign, pantCampaign, womanCampaign,
                jewelryCampaign, skirtCampaign);

        Coupon coupon = new Coupon(1L, new BigDecimal("3000.0"), new BigDecimal("20.0"), DiscountType.RATE);
        shoppingCart.applyCoupon(coupon);

        String result = "CART\t\n" +
                "Items\t\n" +
                "[\t\n" +
                "ShoppingCartItem{product=Product{id=1, name='tShirtProduct', price=100.0, category=Category{id=5, name='T-Shirt'}}, quantity=10}, \t\n" +
                "ShoppingCartItem{product=Product{id=2, name='pantProduct', price=200.0, category=Category{id=4, name='Pants'}}, quantity=20}, \t\n" +
                "ShoppingCartItem{product=Product{id=3, name='pantProduct1', price=300.0, category=Category{id=4, name='Pants'}}, quantity=20}, \t\n" +
                "ShoppingCartItem{product=Product{id=4, name='jewelryProduct', price=200.0, category=Category{id=6, name='Jewelry'}}, quantity=15}, \t\n" +
                "ShoppingCartItem{product=Product{id=5, name='skirtProduct', price=100.0, category=Category{id=7, name='Skirt'}}, quantity=10}]\t\n" +
                "\t\n" +
                "\t\n" +
                "Values\t\n" +
                "Total Amount Before Discount:15000.0\t\n" +
                "Total Campaign Discount:4100.00\t\n" +
                "Total Amount After Campaign Discount:10900.00\t\n" +
                "Total Coupon Discount: 2180.00\t\n" +
                "TotalAmount After Discount:8720.00\t\n" +
                "Total Discount: 6280.00\t\n" +
                "Delivery Cost: 63.45\t\n";

        assertEquals(result, shoppingCart.toString());
        shoppingCart.print();
        assertEquals(result, shoppingCart.toString());
    }


}

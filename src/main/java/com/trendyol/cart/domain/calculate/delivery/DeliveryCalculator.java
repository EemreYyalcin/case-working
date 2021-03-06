package com.trendyol.cart.domain.calculate.delivery;

import com.trendyol.cart.domain.product.Product;
import com.trendyol.cart.domain.shopping.ShoppingCart;
import com.trendyol.cart.domain.shopping.CartItem;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class DeliveryCalculator {

    private final BigDecimal fixedCost;

    private final BigDecimal deliveryCost;

    private final BigDecimal productCost;

    public BigDecimal calculateDeliveryCost(ShoppingCart shoppingCart) {
        return calculatePerDelivery(shoppingCart).add(calculatePerProducts(shoppingCart)).add(fixedCost);
    }

    public BigDecimal calculatePerDelivery(ShoppingCart shoppingCart) {
        long categorySize = shoppingCart.getItems().stream()
                .map(CartItem::getProduct)
                .map(Product::getCategory)
                .distinct().count();

        return deliveryCost.multiply(new BigDecimal(categorySize));
    }

    public BigDecimal calculatePerProducts(ShoppingCart shoppingCart) {
        return productCost.multiply(new BigDecimal(shoppingCart.getItems().size()));
    }


}

package com.trendyol.cart.domain.shopping;

import com.trendyol.cart.domain.product.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class CartItem {

    private final Product product;

    private final AtomicInteger quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = new AtomicInteger(quantity);
    }

    public void addQuantity(int quantity) {
        this.quantity.addAndGet(quantity);
    }

    public BigDecimal getTotalAmount() {
        return product.getPrice().multiply(new BigDecimal(quantity.get()));
    }

    @Override
    public String toString() {
        return "\t\nShoppingCartItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                "}";
    }
}

package com.trendyol.cart.domain.shopping;

import com.trendyol.cart.domain.product.Category;
import com.trendyol.cart.domain.product.Product;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CartItemTest {

    @Test
    public void constructorCreate_SetAllParameter_FieldsNotChange() {

        Long id = 1L;
        String name = "product";
        BigDecimal price = new BigDecimal("100.0");
        Long categoryId = 10L;
        String categoryName = "category";
        Category category = new Category(categoryId, categoryName);
        Product product = new Product(id, name, price, category);
        int quantity = 20;
        CartItem cartItem = new CartItem(product, quantity);

        assertNotNull(cartItem);
        assertEquals(product, cartItem.getProduct());
        assertEquals(quantity, cartItem.getQuantity().get());
    }

    @Test
    public void addQuantity_AddValidQuantity_ResultChangeProductQuantity() {

        Long id = 1L;
        String name = "product";
        BigDecimal price = new BigDecimal("100.0");
        Long categoryId = 10L;
        String categoryName = "category";
        Category category = new Category(categoryId, categoryName);
        Product product = new Product(id, name, price, category);
        int quantity = 20;
        CartItem cartItem = new CartItem(product, quantity);
        cartItem.addQuantity(10);

        assertEquals(quantity + 10, cartItem.getQuantity().get());
    }

  @Test
    public void getTotalAmount_AddValidProductAndQuantity_ResultValidAmount() {
      Long id = 1L;
      String name = "product";
      BigDecimal price = new BigDecimal("100.0");
      Long categoryId = 10L;
      String categoryName = "category";
      Category category = new Category(categoryId, categoryName);
      Product product = new Product(id, name, price, category);
      int quantity = 20;
      CartItem cartItem = new CartItem(product, quantity);

      assertEquals(new BigDecimal("2000.0"), cartItem.getTotalAmount());

    }

    @Test
    public void toString_SetValidObjects_ResultsValidString(){
        Long id = 1L;
        String name = "product";
        BigDecimal price = new BigDecimal("100.0");
        Long categoryId = 10L;
        String categoryName = "category";
        Category category = new Category(categoryId, categoryName);
        Product product = new Product(id, name, price, category);
        int quantity = 20;
        CartItem cartItem = new CartItem(product, quantity);
        assertEquals("\t\nShoppingCartItem{product=Product{id=1, name='product', price=100.0, category=Category{id=10, name='category'}}, quantity=20}", cartItem.toString());
    }

}

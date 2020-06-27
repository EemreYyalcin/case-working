package com.trendyol.cart.domain.product;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Objects;

import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void constructorCreate_SetAllParameter_FieldsNotChange() {
        Long id = 1L;
        String name = "product";
        BigDecimal price = new BigDecimal("100.0");
        Long categoryId = 10L;
        String categoryName = "category";
        Category category = new Category(categoryId, categoryName);

        Product product = new Product(id, name, price, category);

        assertNotNull(product);
        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        assertEquals(price, product.getPrice());
        assertEquals(category, product.getCategory());
    }

    @Test
    public void hashCode_SetInvalidCode_ResultIsFalse() {
        Long id = 1L;
        String name = "product";
        BigDecimal price = new BigDecimal("100.0");
        Long categoryId = 10L;
        String categoryName = "category";
        Category category = new Category(categoryId, categoryName);
        Product product = new Product(id, name, price, category);

        assertNotEquals(Objects.hash(2L), product.hashCode());
    }

    @Test
    public void hashCode_SetValidCode_ResultIsTrue() {
        Long id = 1L;
        String name = "product";
        BigDecimal price = new BigDecimal("100.0");
        Long categoryId = 10L;
        String categoryName = "category";
        Category category = new Category(categoryId, categoryName);
        Product product = new Product(id, name, price, category);

        assertEquals(Objects.hash(id), product.hashCode());
    }

    @Test
    public void equals_SetSameObject_ResultsIsTrue() {
        Long id = 1L;
        String name = "product";
        BigDecimal price = new BigDecimal("100.0");
        Long categoryId = 10L;
        String categoryName = "category";
        Category category = new Category(categoryId, categoryName);
        Product product1 = new Product(id, name, price, category);
        Product product2 = new Product(id, name, price, category);

        assertEquals(product1, product2);

    }

    @Test
    public void equals_SetInvalidObjects_ResultsIsFalse() {
        Long id = 1L;
        Long id2 = 3L;
        String name = "product";
        BigDecimal price = new BigDecimal("100.0");
        Long categoryId = 10L;
        String categoryName = "category";
        Category category = new Category(categoryId, categoryName);
        Product product1 = new Product(id, name, price, category);
        Product product2 = new Product(id2, name, price, category);

        assertNotEquals(product1, product2);
        assertNotEquals(product1, null);
        assertNotEquals(product1, new Object());
    }

    @Test
    public void toString_SetValidObjects_ResultsValidString() {
        Long id = 1L;
        String name = "product";
        BigDecimal price = new BigDecimal("100.0");
        Long categoryId = 10L;
        String categoryName = "category";
        Category category = new Category(categoryId, categoryName);
        Product product = new Product(id, name, price, category);

        assertEquals("Product{id=1, name='product', price=100.0, category=Category{id=10, name='category'}}", product.toString());
    }


}

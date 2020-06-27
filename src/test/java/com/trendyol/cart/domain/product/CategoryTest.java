package com.trendyol.cart.domain.product;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class CategoryTest {

    @Test
    public void constructorCreate_SetAllParameter_FieldsNotChange() {
        Long id = 1L;
        String name = "test";
        Category category = new Category(id, name);
        assertEquals(id, category.getId());
        assertEquals(name, category.getName());
        assertNull(category.getParentCategory());
    }

    @Test
    public void getAllParentCategory_SetValidParentCategory_ResultsValidList() {
        Long id = 1L;
        String name = "test";
        Category category = new Category(id, name);
        Long idParent = 2L;
        String nameParent = "testParent";
        Category categoryParent = new Category(idParent, nameParent);
        Long idParentParent = 3L;
        String nameParentParent = "testParentParent";
        Category categoryParentParent = new Category(idParentParent, nameParentParent);
        category.setParentCategory(categoryParent);
        categoryParent.setParentCategory(categoryParentParent);

        List<Category> categories = new ArrayList<Category>() {{
            add(category);
            add(categoryParent);
            add(categoryParentParent);
        }};

        assertEquals(categories, category.getAllParentCategory(new ArrayList<>()));
    }

    @Test
    public void isParentCategory_SetMissingParentCategory_ResultFalse() {
        Long id = 1L;
        String name = "test";
        Category category = new Category(id, name);
        Long idNotParent = 2L;
        String nameNotParent = "testNotParent";
        Category categoryNotParent = new Category(idNotParent, nameNotParent);
        Long idParent = 3L;
        String nameParent = "testParent";
        Category categoryParent = new Category(idParent, nameParent);
        category.setParentCategory(categoryParent);

        assertFalse(category.isParentCategory(categoryNotParent));
        assertFalse(category.isParentCategory(null));
        assertTrue(category.isParentCategory(categoryParent));

    }


    @Test
    public void hashCode_SetInvalidCode_ResultIsFalse() {
        Long id = 1L;
        String name = "test";
        Category category = new Category(id, name);
        assertNotEquals(Objects.hash(2L), category.hashCode());
    }

    @Test
    public void hashCode_SetValidCode_ResultIsTrue() {
        Long id = 1L;
        String name = "test";
        Category category = new Category(id, name);
        assertEquals(Objects.hash(id), category.hashCode());
    }

    @Test
    public void equals_SetSameObject_ResultsIsTrue() {
        Long id = 1L;
        String name = "test";
        Category category1 = new Category(id, name);
        Category category2 = new Category(id, name);

        assertEquals(category1, category2);
    }

    @Test
    public void equals_SetInvalidObjects_ResultsIsFalse() {
        Long id = 1L;
        String name = "test";
        Category category1 = new Category(id, name);
        Category category2 = new Category(2L, name);

        assertNotEquals(category1, category2);
        assertNotEquals(category1, null);
        assertNotEquals(category1, new Object());
    }

    @Test
    public void toString_SetValidObjects_ResultsValidString() {
        Long id = 1L;
        String name = "test";
        Category category = new Category(id, name);

        assertEquals("Category{id=1, name='test'}", category.toString());
    }

}

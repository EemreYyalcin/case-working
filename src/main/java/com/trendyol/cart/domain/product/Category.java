package com.trendyol.cart.domain.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Getter
public class Category {

    private final Long id;

    private final String name;

    @Setter
    private Category parentCategory;

    public List<Category> getAllParentCategory(List<Category> parents) {
        parents.add(this);
        if (parentCategory == null) {
            return parents;
        }
        return parentCategory.getAllParentCategory(parents);
    }

    public boolean isParentCategory(Category category) {
        if (parentCategory == null) {
            return false;
        }
        if (parentCategory.equals(category)) {
            return true;
        }
        return parentCategory.isParentCategory(category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(name, category.name) &&
                Objects.equals(parentCategory, category.parentCategory);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

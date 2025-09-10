package com.vvt.shop.dao;

import com.vvt.shop.model.Category;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    public List<Category> findAll() { return new ArrayList<>(JsonStore.getCategories()); }
    public Category findBySlug(String slug) { return JsonStore.findCategoryBySlug(slug); }
    public Category findById(int id) { return JsonStore.findCategoryById(id); }
    public void create(Category c) { JsonStore.createCategory(c); }
    public void update(Category c) { JsonStore.updateCategory(c); }
    public void delete(int id) { JsonStore.deleteCategory(id); }
}

package com.vvt.shop.dao;

import com.google.gson.Gson;
import com.vvt.shop.model.Category;
import com.vvt.shop.model.Product;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class JsonStore {
    public static class SeedData {
        public java.util.List<Category> categories;
        public java.util.List<Product> products;
    }

    private static final java.util.List<Category> categories = new java.util.ArrayList<>();
    private static final java.util.List<Product> products = new java.util.ArrayList<>();
    private static int nextProductId = 1;
    private static int nextCategoryId = 1;

    static {
        try {
            InputStream is = JsonStore.class.getResourceAsStream("/seed.json");
            if (is == null) throw new RuntimeException("seed.json not found in resources");
            try (InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
                SeedData data = new Gson().fromJson(reader, SeedData.class);
                if (data.categories != null) categories.addAll(data.categories);
                if (data.products != null) products.addAll(data.products);
                nextCategoryId = categories.stream().mapToInt(Category::getId).max().orElse(0) + 1;
                nextProductId = products.stream().mapToInt(Product::getId).max().orElse(0) + 1;
            }
        } catch (Exception e) {
            throw new RuntimeException("Cannot load JSON seed", e);
        }
    }

    // ----- Category ops -----
    public static java.util.List<Category> getCategories() {
        return java.util.Collections.unmodifiableList(categories);
    }
    public static Category findCategoryById(int id) {
        return categories.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }
    public static Category findCategoryBySlug(String slug) {
        return categories.stream().filter(c -> slug.equals(c.getSlug())).findFirst().orElse(null);
    }
    public static void createCategory(Category c) {
        c.setId(nextCategoryId++);
        categories.add(c);
    }
    public static void updateCategory(Category c) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == c.getId()) { categories.set(i, c); return; }
        }
    }
    public static void deleteCategory(int id) {
        categories.removeIf(c -> c.getId() == id);
        products.removeIf(p -> p.getCategoryId() == id); // cascade remove
    }

    // ----- Product ops -----
    public static java.util.List<Product> listProducts(Integer categoryId, String q) {
        return products.stream()
                .filter(p -> categoryId == null || p.getCategoryId() == categoryId)
                .filter(p -> q == null || q.isBlank() || p.getName().toLowerCase().contains(q.toLowerCase()))
                .sorted(java.util.Comparator.comparing(Product::getId).reversed())
                .collect(Collectors.toList());
    }
    public static Product findProductById(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
    public static Product findProductBySlug(String slug) {
        return products.stream().filter(p -> slug.equals(p.getSlug())).findFirst().orElse(null);
    }
    public static void createProduct(Product p) {
        p.setId(nextProductId++);
        products.add(p);
    }
    public static void updateProduct(Product p) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == p.getId()) { products.set(i, p); return; }
        }
    }
    public static void deleteProduct(int id) {
        products.removeIf(p -> p.getId() == id);
    }
}

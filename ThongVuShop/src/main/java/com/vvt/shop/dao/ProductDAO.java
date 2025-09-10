package com.vvt.shop.dao;

import com.vvt.shop.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public List<Product> list(int page, int pageSize, Integer categoryId, String q) {
        List<Product> all = JsonStore.listProducts(categoryId, q);
        int from = Math.max(0, (page - 1) * pageSize);
        int to = Math.min(all.size(), from + pageSize);
        if (from >= to) return new ArrayList<>();
        return new ArrayList<>(all.subList(from, to));
    }
    public Product findBySlug(String slug) { return JsonStore.findProductBySlug(slug); }
    public Product findById(int id) { return JsonStore.findProductById(id); }
    public void create(Product p) { JsonStore.createProduct(p); }
    public void update(Product p) { JsonStore.updateProduct(p); }
    public void delete(int id) { JsonStore.deleteProduct(id); }
}

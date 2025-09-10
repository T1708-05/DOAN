package com.vvt.shop.model;
import java.math.BigDecimal;
public class Product {
    private int id; private int categoryId; private String name; private String slug;
    private String description; private BigDecimal price; private int stock; private String imageUrl;
    public int getId() { return id; } public void setId(int id) { this.id = id; }
    public int getCategoryId() { return categoryId; } public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public String getSlug() { return slug; } public void setSlug(String slug) { this.slug = slug; }
    public String getDescription() { return description; } public void setDescription(String description) { this.description = description; }
    public BigDecimal getPrice() { return price; } public void setPrice(BigDecimal price) { this.price = price; }
    public int getStock() { return stock; } public void setStock(int stock) { this.stock = stock; }
    public String getImageUrl() { return imageUrl; } public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}

package com.vvt.shop.model;
import java.math.BigDecimal;
public class CartItem {
    private Product product; private int quantity;
    public CartItem(Product product, int quantity) { this.product=product; this.quantity=quantity; }
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int q) { this.quantity = q; }
    public BigDecimal getSubTotal() { return product.getPrice().multiply(BigDecimal.valueOf(quantity)); }
}

package com.vvt.shop.model;
import java.math.BigDecimal;
public class OrderItem {
    private long id; private long orderId; private int productId; private int quantity; private BigDecimal unitPrice;
    public long getId() { return id; } public void setId(long id) { this.id = id; }
    public long getOrderId() { return orderId; } public void setOrderId(long orderId) { this.orderId = orderId; }
    public int getProductId() { return productId; } public void setProductId(int productId) { this.productId = productId; }
    public int getQuantity() { return quantity; } public void setQuantity(int quantity) { this.quantity = quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; } public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
}

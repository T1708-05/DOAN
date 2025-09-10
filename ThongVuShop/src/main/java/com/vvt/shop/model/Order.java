package com.vvt.shop.model;
import java.math.BigDecimal; import java.util.*;
public class Order {
    private long id; private int userId; private BigDecimal total; private String status;
    private String shippingName, shippingPhone, shippingAddress; private Date createdAt;
    private java.util.List<OrderItem> items = new ArrayList<>();
    public long getId() { return id; } public void setId(long id) { this.id = id; }
    public int getUserId() { return userId; } public void setUserId(int userId) { this.userId = userId; }
    public BigDecimal getTotal() { return total; } public void setTotal(BigDecimal total) { this.total = total; }
    public String getStatus() { return status; } public void setStatus(String status) { this.status = status; }
    public String getShippingName() { return shippingName; } public void setShippingName(String shippingName) { this.shippingName = shippingName; }
    public String getShippingPhone() { return shippingPhone; } public void setShippingPhone(String shippingPhone) { this.shippingPhone = shippingPhone; }
    public String getShippingAddress() { return shippingAddress; } public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
    public Date getCreatedAt() { return createdAt; } public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public java.util.List<OrderItem> getItems() { return items; }
}

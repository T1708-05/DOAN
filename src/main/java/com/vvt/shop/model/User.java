package com.vvt.shop.model;
public class User {
    private int id; private String fullName; private String email; private String passwordHash; private String role;
    public int getId() { return id; } public void setId(int id) { this.id = id; }
    public String getFullName() { return fullName; } public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; } public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return passwordHash; } public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getRole() { return role; } public void setRole(String role) { this.role = role; }
    public boolean isAdmin() { return "ADMIN".equals(role); }
}

package com.vvt.shop.util;
public class Validation {
    public static boolean notEmpty(String s) { return s != null && !s.trim().isEmpty(); }
    public static boolean email(String s) { return s != null && s.matches("^[^@]+@[^@]+\\.[^@]+$"); }
    public static boolean phone(String s) { return s != null && s.matches("[0-9+()\\- ]{8,}"); }
}

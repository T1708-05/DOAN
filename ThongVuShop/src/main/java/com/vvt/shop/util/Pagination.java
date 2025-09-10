package com.vvt.shop.util;
public class Pagination { public static int page(String p) { try { return Math.max(Integer.parseInt(p), 1); } catch(Exception e){ return 1; } } }

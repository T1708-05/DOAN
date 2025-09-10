package com.vvt.shop.dao;

import com.vvt.shop.model.Order;

public class OrderDAO {
    private static long seq = System.currentTimeMillis();
    public long create(Order order) {
        // JSON mode: no persistence; return a pseudo ID
        return ++seq;
    }
}

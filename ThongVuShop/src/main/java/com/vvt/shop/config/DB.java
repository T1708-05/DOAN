package com.vvt.shop.config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DB {
    private static DataSource ds;
    static {
        try {
            Context init = new InitialContext();
            Context env = (Context) init.lookup("java:/comp/env");
            ds = (DataSource) env.lookup("jdbc/ShopDB");
        } catch (NamingException e) {
            throw new RuntimeException("Cannot init DataSource", e);
        }
    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}

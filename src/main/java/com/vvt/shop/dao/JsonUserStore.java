package com.vvt.shop.dao;

import com.google.gson.Gson;
import com.vvt.shop.model.User;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonUserStore {
    private static final List<User> users = new ArrayList<>();
    private static int nextId = 1;

    static {
        try {
            InputStream is = JsonUserStore.class.getResourceAsStream("/users.json");
            if (is != null) {
                try (InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
                    UsersData data = new Gson().fromJson(reader, UsersData.class);
                    if (data != null && data.users != null) users.addAll(data.users);
                }
            }
            for (User u : users) nextId = Math.max(nextId, u.getId() + 1);
        } catch (Exception e) {
            throw new RuntimeException("Cannot load users.json", e);
        }
    }

    private static class UsersData { List<User> users; }

    public static List<User> all() { return Collections.unmodifiableList(users); }

    public static User findByEmail(String email) {
        for (User u : users) if (u.getEmail().equalsIgnoreCase(email)) return u;
        return null;
    }

    public static User create(String fullName, String email, String passwordHashOrPlain, String role) {
        User u = new User();
        u.setId(nextId++);
        u.setFullName(fullName);
        u.setEmail(email);
        u.setPasswordHash(passwordHashOrPlain);
        u.setRole(role);
        users.add(u);
        return u;
    }
}

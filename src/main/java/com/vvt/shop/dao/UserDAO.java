package com.vvt.shop.dao;

import com.vvt.shop.model.User;
import com.vvt.shop.util.BCryptUtil;

public class UserDAO {
    private User map(User u) { return u; }

    public User findByEmail(String email) {
        return JsonUserStore.findByEmail(email);
    }

    public User create(String fullName, String email, String password) {
        // In JSON mode, keep bcrypt hash if lib present; otherwise plain
        String hash;
        try { hash = BCryptUtil.hash(password); } catch (Throwable t) { hash = password; }
        return JsonUserStore.create(fullName, email, hash, "USER");
    }

    public User authenticate(String email, String password) {
        User u = findByEmail(email);
        if (u == null) return null;
        String ph = u.getPasswordHash();
        boolean ok = false;
        if (ph != null && ph.startsWith("$2")) {
            ok = BCryptUtil.verify(password, ph);
        } else {
            ok = ph != null && ph.equals(password);
        }
        return ok ? u : null;
    }
}

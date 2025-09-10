package com.vvt.shop.util;
import java.security.SecureRandom; import java.util.Base64;
import jakarta.servlet.http.HttpServletRequest; import jakarta.servlet.http.HttpSession;
public class CSRFUtil {
    private static final SecureRandom RANDOM = new SecureRandom();
    public static String ensureToken(HttpServletRequest req) {
        HttpSession s = req.getSession();
        String token = (String) s.getAttribute("csrf_token");
        if (token == null) {
            byte[] buf = new byte[32]; RANDOM.nextBytes(buf);
            token = Base64.getUrlEncoder().withoutPadding().encodeToString(buf);
            s.setAttribute("csrf_token", token);
        }
        return token;
    }
    public static boolean valid(HttpServletRequest req) {
        String form = req.getParameter("csrf_token");
        String sess = (String) req.getSession().getAttribute("csrf_token");
        return sess != null && sess.equals(form);
    }
}

package com.vvt.shop.filter;
import com.vvt.shop.util.CSRFUtil;
import jakarta.servlet.*; import jakarta.servlet.http.*; import java.io.IOException;
public class CSRFProtectionFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request; HttpServletResponse resp = (HttpServletResponse) response;
        // Always ensure token exists, also expose as request attribute for JSP forms
        String token = CSRFUtil.ensureToken(req);
        req.setAttribute("csrf_token", token);
        if ("POST".equalsIgnoreCase(req.getMethod())) {
            if (!CSRFUtil.valid(req)) { resp.sendError(403, "CSRF token invalid"); return; }
        }
        chain.doFilter(request, response);
    }
}

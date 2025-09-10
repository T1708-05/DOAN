package com.vvt.shop.filter;
import jakarta.servlet.*; import jakarta.servlet.http.*; import java.io.IOException;
public class AuthFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request; HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getSession().getAttribute("auth") == null) { resp.sendRedirect(req.getContextPath()+"/login"); return; }
        chain.doFilter(request, response);
    }
}

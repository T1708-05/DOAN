package com.vvt.shop.filter;
import com.vvt.shop.model.User;
import jakarta.servlet.*; import jakarta.servlet.http.*; import java.io.IOException;
public class AdminFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request; HttpServletResponse resp = (HttpServletResponse) response;
        Object o = req.getSession().getAttribute("auth");
        if (o instanceof User && ((User)o).isAdmin()) { chain.doFilter(request,response); return; }
        resp.sendError(403);
    }
}

package com.vvt.shop.controller;
import com.vvt.shop.dao.ProductDAO; import com.vvt.shop.model.*;
import jakarta.servlet.*; import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*; import java.io.IOException; import java.util.*;
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private final ProductDAO productDAO = new ProductDAO();
    @SuppressWarnings("unchecked") private Map<Integer, CartItem> cart(HttpServletRequest req) {
        HttpSession s = req.getSession(); Map<Integer, CartItem> cart = (Map<Integer, CartItem>) s.getAttribute("cart");
        if (cart == null) { cart = new LinkedHashMap<>(); s.setAttribute("cart", cart); } return cart;
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/cart/cart.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            String slug = req.getParameter("slug"); int qty = Integer.parseInt(req.getParameter("qty"));
            Product p = productDAO.findBySlug(slug);
            if (p!=null) { Map<Integer, CartItem> cart = cart(req); CartItem it = cart.get(p.getId());
                if (it==null) cart.put(p.getId(), new CartItem(p, qty)); else it.setQuantity(it.getQuantity()+qty); }
            resp.sendRedirect(req.getContextPath()+"/cart");
        } else if ("update".equals(action)) {
            Map<Integer, CartItem> cart = cart(req);
            for (String k : req.getParameterMap().keySet()) if (k.startsWith("qty_")) {
                int id = Integer.parseInt(k.substring(4)); int q = Integer.parseInt(req.getParameter(k));
                CartItem it = cart.get(id); if (it!=null) it.setQuantity(Math.max(1,q));
            } resp.sendRedirect(req.getContextPath()+"/cart");
        } else if ("remove".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id")); cart(req).remove(id); resp.sendRedirect(req.getContextPath()+"/cart");
        } else { resp.sendError(400); }
    }
}

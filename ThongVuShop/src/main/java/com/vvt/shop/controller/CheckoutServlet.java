package com.vvt.shop.controller;
import com.vvt.shop.dao.OrderDAO; import com.vvt.shop.model.*; import com.vvt.shop.util.Validation;
import jakarta.servlet.*; import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*; import java.io.IOException; import java.math.BigDecimal; import java.util.*;
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private final OrderDAO orderDAO = new OrderDAO();
    @SuppressWarnings("unchecked") private Map<Integer, CartItem> cart(HttpServletRequest req) {
        return (Map<Integer, CartItem>) req.getSession().getAttribute("cart");
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (cart(req)==null || cart(req).isEmpty()) { resp.sendRedirect(req.getContextPath()+"/cart"); return; }
        req.getRequestDispatcher("/WEB-INF/views/cart/checkout.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Integer, CartItem> cart = cart(req); if (cart==null || cart.isEmpty()) { resp.sendRedirect(req.getContextPath()+"/cart"); return; }
        String name=req.getParameter("shipping_name"), phone=req.getParameter("shipping_phone"), addr=req.getParameter("shipping_address");
        if (!Validation.notEmpty(name) || !Validation.phone(phone) || !Validation.notEmpty(addr)) {
            req.setAttribute("error","Thông tin giao hàng không hợp lệ"); doGet(req, resp); return;
        }
        User user = (User) req.getSession().getAttribute("auth");
        Order od = new Order(); od.setUserId(user.getId()); od.setShippingName(name); od.setShippingPhone(phone); od.setShippingAddress(addr);
        java.math.BigDecimal total = BigDecimal.ZERO;
        for (CartItem it : cart.values()) {
            OrderItem oi = new OrderItem(); oi.setProductId(it.getProduct().getId()); oi.setQuantity(it.getQuantity()); oi.setUnitPrice(it.getProduct().getPrice());
            od.getItems().add(oi); total = total.add(it.getSubTotal());
        }
        od.setTotal(total); long orderId = orderDAO.create(od);
        req.getSession().removeAttribute("cart"); resp.sendRedirect(req.getContextPath()+"/order/"+orderId);
    }
}

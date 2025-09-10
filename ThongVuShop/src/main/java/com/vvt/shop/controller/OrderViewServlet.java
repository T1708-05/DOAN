package com.vvt.shop.controller;
import jakarta.servlet.*; import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*; import java.io.IOException;
@WebServlet("/order/*")
public class OrderViewServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo(); // /{id}
        req.setAttribute("orderId", path!=null ? path.substring(1) : "?");
        req.getRequestDispatcher("/WEB-INF/views/cart/order-success.jsp").forward(req, resp);
    }
}

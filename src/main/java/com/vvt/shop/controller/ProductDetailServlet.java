package com.vvt.shop.controller;
import com.vvt.shop.dao.ProductDAO; import com.vvt.shop.model.Product;
import jakarta.servlet.*; import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*; import java.io.IOException;
@WebServlet("/product")
public class ProductDetailServlet extends HttpServlet {
    private final ProductDAO productDAO = new ProductDAO();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String slug = req.getParameter("slug"); Product p = productDAO.findBySlug(slug);
        if (p==null) { resp.sendError(404); return; }
        req.setAttribute("p", p); req.getRequestDispatcher("/WEB-INF/views/product/detail.jsp").forward(req, resp);
    }
}

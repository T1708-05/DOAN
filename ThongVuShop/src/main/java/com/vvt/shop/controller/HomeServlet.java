package com.vvt.shop.controller;
import com.vvt.shop.dao.*; import com.vvt.shop.model.*; import com.vvt.shop.config.AppConfig; import com.vvt.shop.util.Pagination;
import jakarta.servlet.*; import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*; import java.io.IOException; import java.util.*;
@WebServlet(name="home", urlPatterns = {"", "/", "/home"})
public class HomeServlet extends HttpServlet {
    private final CategoryDAO categoryDAO = new CategoryDAO(); private final ProductDAO productDAO = new ProductDAO();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = Pagination.page(req.getParameter("page"));
        req.setAttribute("categories", categoryDAO.findAll());
        req.setAttribute("products", productDAO.list(page, AppConfig.PAGE_SIZE, null, null));
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}

package com.vvt.shop.controller;
import com.vvt.shop.dao.*; import com.vvt.shop.model.*; import com.vvt.shop.util.Pagination; import com.vvt.shop.config.AppConfig;
import jakarta.servlet.*; import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*; import java.io.IOException; import java.util.*;
@WebServlet("/products")
public class ProductListServlet extends HttpServlet {
    private final CategoryDAO categoryDAO = new CategoryDAO(); private final ProductDAO productDAO = new ProductDAO();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer catId = null; try { catId = req.getParameter("cat")!=null ? Integer.parseInt(req.getParameter("cat")) : null; } catch(Exception ignored){}
        String q = req.getParameter("q"); int page = Pagination.page(req.getParameter("page"));
        req.setAttribute("categories", categoryDAO.findAll());
        req.setAttribute("products", productDAO.list(page, AppConfig.PAGE_SIZE, catId, q));
        req.getRequestDispatcher("/WEB-INF/views/product/list.jsp").forward(req, resp);
    }
}

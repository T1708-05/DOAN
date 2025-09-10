package com.vvt.shop.controller;
import com.vvt.shop.dao.*; import com.vvt.shop.model.*;
import jakarta.servlet.*; import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*; import java.io.IOException; import java.math.BigDecimal;
@WebServlet({"/admin/products","/admin/product/new","/admin/product/edit","/admin/product/delete"})
public class AdminProductServlet extends HttpServlet {
    private final ProductDAO productDAO = new ProductDAO(); private final CategoryDAO categoryDAO = new CategoryDAO();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/admin/products":
                req.setAttribute("products", productDAO.list(1, 1000, null, null));
                req.getRequestDispatcher("/WEB-INF/views/admin/product-list.jsp").forward(req, resp); break;
            case "/admin/product/new":
            case "/admin/product/edit":
                if ("/admin/product/edit".equals(req.getServletPath()) && req.getParameter("id")!=null) {
                    int id = Integer.parseInt(req.getParameter("id"));
                    req.setAttribute("p", productDAO.findById(id));
                }
                req.setAttribute("categories", categoryDAO.findAll());
                req.getRequestDispatcher("/WEB-INF/views/admin/product-form.jsp").forward(req, resp); break;
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if ("/admin/product/delete".equals(path)) {
            int id = Integer.parseInt(req.getParameter("id")); productDAO.delete(id); resp.sendRedirect(req.getContextPath()+"/admin/products"); return;
        }
        Product p = new Product();
        if (req.getParameter("id")!=null && !req.getParameter("id").isBlank()) p.setId(Integer.parseInt(req.getParameter("id")));
        p.setCategoryId(Integer.parseInt(req.getParameter("category_id"))); p.setName(req.getParameter("name")); p.setSlug(req.getParameter("slug"));
        p.setDescription(req.getParameter("description")); p.setPrice(new BigDecimal(req.getParameter("price")));
        p.setStock(Integer.parseInt(req.getParameter("stock"))); p.setImageUrl(req.getParameter("image_url"));
        if (p.getId()>0) productDAO.update(p); else productDAO.create(p); resp.sendRedirect(req.getContextPath()+"/admin/products");
    }
}

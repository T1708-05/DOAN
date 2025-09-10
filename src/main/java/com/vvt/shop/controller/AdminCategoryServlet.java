package com.vvt.shop.controller;
import com.vvt.shop.dao.*; import com.vvt.shop.model.*;
import jakarta.servlet.*; import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*; import java.io.IOException;
@WebServlet({"/admin/categories","/admin/category/new","/admin/category/edit","/admin/category/delete"})
public class AdminCategoryServlet extends HttpServlet {
    private final CategoryDAO categoryDAO = new CategoryDAO();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/admin/categories":
                req.setAttribute("categories", categoryDAO.findAll());
                req.getRequestDispatcher("/WEB-INF/views/admin/category-list.jsp").forward(req, resp); break;
            case "/admin/category/new":
            case "/admin/category/edit":
                if ("/admin/category/edit".equals(req.getServletPath()) && req.getParameter("id")!=null) {
                    int id = Integer.parseInt(req.getParameter("id")); req.setAttribute("c", categoryDAO.findById(id));
                }
                req.getRequestDispatcher("/WEB-INF/views/admin/category-form.jsp").forward(req, resp); break;
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if ("/admin/category/delete".equals(path)) {
            int id = Integer.parseInt(req.getParameter("id")); categoryDAO.delete(id); resp.sendRedirect(req.getContextPath()+"/admin/categories"); return;
        }
        Category c = new Category();
        if (req.getParameter("id")!=null && !req.getParameter("id").isBlank()) c.setId(Integer.parseInt(req.getParameter("id")));
        c.setName(req.getParameter("name")); c.setSlug(req.getParameter("slug"));
        if (c.getId()>0) categoryDAO.update(c); else categoryDAO.create(c);
        resp.sendRedirect(req.getContextPath()+"/admin/categories");
    }
}

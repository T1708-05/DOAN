package com.vvt.shop.controller;
import com.vvt.shop.dao.UserDAO; import com.vvt.shop.model.User; import com.vvt.shop.util.Validation;
import jakarta.servlet.*; import jakarta.servlet.annotation.WebServlet; import jakarta.servlet.http.*; import java.io.IOException;
@WebServlet({"/login","/register","/logout"})
public class AuthServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/login": req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, resp); break;
            case "/register": req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req, resp); break;
            case "/logout": req.getSession().invalidate(); resp.sendRedirect(req.getContextPath()+"/"); break;
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/login": {
                User u = userDAO.authenticate(req.getParameter("email"), req.getParameter("password"));
                if (u==null) { req.setAttribute("error","Sai email hoặc mật khẩu"); doGet(req,resp); return; }
                req.getSession().setAttribute("auth", u); resp.sendRedirect(req.getContextPath()+"/"); break; }
            case "/register": {
                String name=req.getParameter("full_name"), email=req.getParameter("email"), pw=req.getParameter("password");
                if (!Validation.notEmpty(name) || !Validation.email(email) || pw==null || pw.length()<6) { req.setAttribute("error","Dữ liệu không hợp lệ"); doGet(req,resp); return; }
                if (userDAO.findByEmail(email)!=null) { req.setAttribute("error","Email đã tồn tại"); doGet(req,resp); return; }
                User u = userDAO.create(name,email,pw); req.getSession().setAttribute("auth", u); resp.sendRedirect(req.getContextPath()+"/"); break; }
        }
    }
}

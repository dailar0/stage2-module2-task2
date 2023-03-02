package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object user = req.getSession().getAttribute("user");
        if (user == null)
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        else {
            resp.sendRedirect(req.getContextPath() + "/user/hello");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object user = req.getParameter("login");
        String password = req.getParameter("password");
        if (Users.getInstance().getUsers().contains(user) && !password.trim().isEmpty()) {
            req.getSession().setAttribute("user", user);
        }

        doGet(req, resp);
    }
}

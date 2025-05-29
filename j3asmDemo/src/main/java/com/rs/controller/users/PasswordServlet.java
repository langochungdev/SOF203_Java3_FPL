package com.rs.controller.users;

import com.rs.dao.UserDAO;
import com.rs.entity.User;
import com.rs.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.rs.util.encrypt.PasswordUtil;
import com.rs.util.other.XMailer;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class PasswordServlet
 */
@WebServlet({"/user/changePass", "/user/forgetPass", "/user/forgetPass/confirm"})
public class PasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String path = request.getServletPath();
        if (path.contains("changePass")) {
            request.setAttribute("view", "/user/changePass.jsp");
        } else if (path.endsWith("forgetPass")) {
            if (request.getSession().getAttribute("isConfirm") == null) {
                request.getSession().setAttribute("isConfirm", false);
            }
            request.setAttribute("view", "/user/forgetPass.jsp");
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String path = request.getServletPath();
        if (path.endsWith("changePass")) {
            try {
                new UserService(request, response).changePass();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (path.endsWith("forgetPass")) {
            try {
                new UserService(request, response).forgetPass();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (path.endsWith("confirm")) {
            new UserService(request, response).confirmEmail();
        }
    }
}

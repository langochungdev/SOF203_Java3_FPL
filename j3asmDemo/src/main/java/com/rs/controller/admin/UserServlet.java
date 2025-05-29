package com.rs.controller.admin;

import com.rs.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * Servlet implementation class UserServlet
 */
@MultipartConfig()
@WebServlet({"/admin/user/list", "/admin/user/edit/*", "/admin/user/blank", "/admin/user/create", "/admin/user/update",
        "/admin/user/delete", "/admin/user/reset"})
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String uri = request.getServletPath();
        try {
            if (uri.contains("edit") || uri.contains("blank")) {
                new UserService(request, response).detail();
            } else if (uri.endsWith("list")) {
                new UserService(request, response).list();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        try {
            new UserService(request, response).runCrud();
        } catch (SQLException | ClassNotFoundException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("path", "/admin/userDetail.jsp");
        request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
    }

}

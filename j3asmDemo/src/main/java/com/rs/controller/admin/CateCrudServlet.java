package com.rs.controller.admin;

import com.rs.dao.CategoryDAO;
import com.rs.entity.Category;
import com.rs.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet implementation class CateCrudServlet
 */
@WebServlet({"/admin/category", "/admin/category/update/*", "/admin/category/delete/*", "/admin/category/new", "/admin/category/insert" })
public class CateCrudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		if(path.endsWith("category")) {
            try {
                new CategoryService(request, response).listPage();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
		else {
			try {
				new CategoryService(request, response).IUD();
			} catch (SQLException | ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		request.setAttribute("path", "/admin/category.jsp");
		request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
	}
}

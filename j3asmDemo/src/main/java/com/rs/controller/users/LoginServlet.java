package com.rs.controller.users;

import com.rs.service.UserService;
import com.rs.util.other.XCookie;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({"/user/login", "/user/logout"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		if(path.contains("login")) {
            try {
                new UserService(request,response).login();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
		else if(path.contains("logout")) {
				request.getSession().setAttribute("currUser", null);
				XCookie xCookie = new XCookie(request, response);
				xCookie.delete("rememberMe");
				response.sendRedirect(request.getContextPath() + "/user/home");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        try {
            new UserService(request,response).login();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

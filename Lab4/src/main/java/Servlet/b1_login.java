package Servlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/account/login")
public class b1_login extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("message", "Enter username and password");
		req.getRequestDispatcher("/views/b1_login.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("un");
		String password = req.getParameter("pw");
		if (username.equalsIgnoreCase("hung") && password.equals("123")) {
			req.setAttribute("message", "Login successfully");
		} else {
			req.setAttribute("message", "Invalid username or password");
		}
		req.getRequestDispatcher("/views/b1_login.jsp").forward(req, resp);
	}
}

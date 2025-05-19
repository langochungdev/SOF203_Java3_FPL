package Servlet;
import java.io.IOException;
import java.util.Base64;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class b3_Servlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user")) {
					String encoded = cookie.getValue();
					byte[] bytes = Base64.getDecoder().decode(encoded);
					String[] userInfo = new String(bytes).split(",");
					req.setAttribute("username", userInfo[0]);
					req.setAttribute("password", userInfo[1]);
				}
			}
		}
		req.getRequestDispatcher("/views/b3_form.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember-me");
		if (username.equalsIgnoreCase("hung") && password.equals("123")) {
			req.setAttribute("message", "Login successfully!");
			req.getSession().setAttribute("username", username); 

			if (remember != null) {
				byte[] bytes = (username + "," + password).getBytes();
				String userInfo = Base64.getEncoder().encodeToString(bytes);
				Cookie cookie = new Cookie("user", userInfo);
				cookie.setMaxAge(30 * 24 * 60 * 60); // hiệu lực 30 ngày
				cookie.setPath("/");

				resp.addCookie(cookie);
			}
		} else {
			req.setAttribute("message", "Invalid login info!");
		}
		req.getRequestDispatcher("/views/b3_form.jsp").forward(req, resp);
	}

}

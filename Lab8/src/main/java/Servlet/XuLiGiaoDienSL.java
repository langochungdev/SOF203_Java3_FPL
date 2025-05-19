package Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/home", "/contact", "/news"})
public class XuLiGiaoDienSL extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();

		 if (uri.contains("contact")) {
			req.getRequestDispatcher("/views/contact.jsp").forward(req, resp);
		}else if(uri.contains("news")){
			req.getRequestDispatcher("/views/news.jsp").forward(req, resp);
		}
			req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
	}
}

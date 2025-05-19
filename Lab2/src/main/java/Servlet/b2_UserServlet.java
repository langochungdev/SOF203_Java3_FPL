package Servlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/b2_UserServlet")
public class b2_UserServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("message", "wellcome to fpl");
		Map<String, Object> m = new HashMap<String, Object>();;
		m.put("fullname", "La Ngoc Hung");
		m.put("gender", "Male");
		m.put("country", "VietNam");
		req.setAttribute("user", m);
		
		req.getRequestDispatcher("/views/b2_page.jsp").forward(req, resp);
	}
}

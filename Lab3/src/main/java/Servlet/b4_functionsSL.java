package Servlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/b4_funtionsServlet")
public class b4_functionsSL extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<>(); 
		map.put("title", "Tiêu đề bản tin"); 
		map.put("content", "Nội dung bản tin thường rất dài"); 
		req.setAttribute("item", map); 
		req.getRequestDispatcher("/views/b4_form.jsp").forward(req, resp);
	}
}
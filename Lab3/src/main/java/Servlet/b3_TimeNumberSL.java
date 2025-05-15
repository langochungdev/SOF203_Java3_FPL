package Servlet;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/b3_TimeNumberSL")
public class b3_TimeNumberSL extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<>(); 
		map.put("name", "iPhone 2024"); 
		map.put("price", 12345.678); 
		map.put("date", new Date()); 
		req.setAttribute("item", map); 
		req.getRequestDispatcher("/views/b3_form.jsp").forward(req, resp);
	}
}

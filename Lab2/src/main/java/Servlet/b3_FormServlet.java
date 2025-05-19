package Servlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/form/update")
public class b3_FormServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> m = new HashMap<String, Object>();;
		m.put("fullname", "La Ngoc Hung");
		m.put("gender", true);
		m.put("country", "VN");
		req.setAttribute("user", m);
		req.setAttribute("editable", true);
		req.getRequestDispatcher("/views/b3_form.jsp").forward(req, resp);
	}
}

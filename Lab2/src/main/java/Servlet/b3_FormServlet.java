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
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> m = new HashMap<String, Object>();;
		m.put("fullname", "La Ngoc Hung");
		m.put("gender", true);
		m.put("country", "VN");
		request.setAttribute("user", m);
		request.setAttribute("editable", true);
		request.getRequestDispatcher("/views/b3_form.jsp").forward(request, response);
	}
}

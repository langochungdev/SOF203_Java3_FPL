package Servlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({ "/calculate/add", "/calculate/sub" })
public class b2_formNhieuNut extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("message", "Nhập số và chọn phép tính");
		req.getRequestDispatcher("/views/b2_form.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String a = req.getParameter("x");
		String b = req.getParameter("y");
		String path = req.getServletPath();
		if (path.endsWith("/add")) {
			double c = Double.valueOf(a) + Double.valueOf(b);
			req.setAttribute("message", a + " + " + b + " = " + c);
		} else {
			double c = Double.valueOf(a) - Double.valueOf(b);
			req.setAttribute("message", a + "-" + b + "=" + c);
		}
		req.getRequestDispatcher("/views/b2_form.jsp").forward(req, resp);
	}
}

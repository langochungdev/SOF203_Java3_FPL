package Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/b2_Servlet")
public class b2_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public b2_Servlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/b2_form.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String from = req.getParameter("from");
		String to = req.getParameter("to");
		String subject = req.getParameter("subject");
		String body = req.getParameter("body");

		b2_Mailer.send(from, to, subject, body);

		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().write("<h2>Email đã được gửi thành công!</h2>");
	}

}

package Servlet;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/b1_CountryServlet")
public class b1_CountryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<b1_Country> list = List.of(
			new b1_Country("VN", "VietNam"),
			new b1_Country("US", "United States"),
			new b1_Country("CN", "China")
		);
		req.setAttribute("countries", list);
		req.getRequestDispatcher("/views/b1_form.jsp").forward(req, resp);
//		req.getRequestDispatcher("/views/b2_form.jsp").forward(req, resp);
	}
}

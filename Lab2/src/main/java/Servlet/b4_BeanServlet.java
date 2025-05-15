package Servlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/b4_BeanServlet")
public class b4_BeanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		b4_User bean = new b4_User();
		bean.setFullname("La Ngoc Hung");
		bean.setGender(true);
		bean.setCountry("VN");
		request.setAttribute("user", bean);
		request.setAttribute("editable", true);
		request.getRequestDispatcher("/views/b4_formBean.jsp").forward(request, response);
	}
}

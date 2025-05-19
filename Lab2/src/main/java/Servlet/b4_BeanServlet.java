package Servlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/b4_BeanServlet")
public class b4_BeanServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		b4_User bean = new b4_User();
		bean.setFullname("La Ngoc Hung");
		bean.setGender(true);
		bean.setCountry("VN");
		req.setAttribute("user", bean);
		req.setAttribute("editable", true);
		req.getRequestDispatcher("/views/b4_formBean.jsp").forward(req, resp);
	}
}

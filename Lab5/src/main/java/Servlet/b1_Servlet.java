package Servlet;
import java.io.IOException;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

@WebServlet("/save")
public class b1_Servlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/b1_form.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		b1_Staff_BeanUtils bean = new b1_Staff_BeanUtils();
		try {
			DateTimeConverter dtc = new DateConverter(new Date());
			dtc.setPattern("dd/MM/yyyy");
			ConvertUtils.register(dtc, Date.class);

			BeanUtils.populate(bean, req.getParameterMap());
//			Thay thế:
//			String name = request.getParameter("name");
//			String email = request.getParameter("email");
//			String ageStr = request.getParameter("age");
//
//			Employee emp = new Employee();
//			emp.setName(name);
//			emp.setEmail(email);
//			emp.setAge(Integer.parseInt(ageStr));
			System.out.println(bean.getFullname());
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("/views/b1_form.jsp").forward(req, resp);
	}

}

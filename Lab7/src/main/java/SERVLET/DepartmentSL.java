package SERVLET;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import DAO.DepartmentDAO;
import DAO.DepartmentDAOImpl;
import ENTITY.Department;

@WebServlet({"/department/index", "/department/edit/*", "/department/create", "/department/update",
		"/department/delete", "/department/reset" })
public class DepartmentSL extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Department form = new Department();
		try {
			BeanUtils.populate(form, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		DepartmentDAO dao = new DepartmentDAOImpl();
		String path = req.getServletPath();
		if (path.contains("edit")) {
			String id = req.getPathInfo().substring(1);
			form = dao.findById(id);
		} else if (path.contains("create")) {
			dao.create(form);
			form = new Department();
		} else if (path.contains("update")) {
			dao.update(form);
		} else if (path.contains("delete")) {
			dao.deleteById(form.getId());
			form = new Department();
		} else {
			form = new Department();
		}
		req.setAttribute("item", form);
		List<Department> list = dao.findAll();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/views/department.jsp").forward(req, resp);
	}
}
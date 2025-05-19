package SERVLET;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import DAO.DepartmentDAOImpl;
import DAO.EmployeeDAO;
import DAO.EmployeeDAOImpl;
import ENTITY.Employee;
import ENTITY.Department;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.BigDecimalConverter;

@MultipartConfig
@WebServlet({ "/employee/index", "/employee/edit/*", "/employee/create", "/employee/update", "/employee/delete", "/employee/reset" })
public class EmployeesServlet extends HttpServlet {
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String method = req.getMethod();
        String path = req.getServletPath();
        EmployeeDAO dao = new EmployeeDAOImpl();
        Employee form = new Employee();
        
    	req.setCharacterEncoding("UTF-8");
        BigDecimalConverter bigDecimalConverter = new BigDecimalConverter(null);
        ConvertUtils.register(bigDecimalConverter, java.math.BigDecimal.class);
        DateConverter dateConverter = new DateConverter(null);
        dateConverter.setPattern("yyyy-MM-dd");
        ConvertUtils.register(dateConverter, java.util.Date.class);

		try {
			BeanUtils.populate(form, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		if (req.getContentType() != null) {
			Part p = req.getPart("photo");
			if (p != null) {
				String fileName = Paths.get(p.getSubmittedFileName()).getFileName().toString();
				String uploadPath = "D:/";
				java.io.File uploadDir = new java.io.File(uploadPath);
				uploadDir.mkdir();
				p.write(uploadPath + "/" + fileName);
				form.setPhoto(fileName);
			}
		}
        
        
        if (path.contains("edit")) {
            String id = req.getPathInfo().substring(1);
            form = dao.findById(id);
        } else if (path.contains("create")) {
            dao.create(form);
            form = new Employee();
        } else if (path.contains("update")) {
            dao.update(form);
        } else if (path.contains("delete")) {
            dao.deleteById(form.getId());
            form = new Employee();
        } else {
            form = new Employee();
        }

        req.setAttribute("item", form);
        List<Employee> list = dao.findAll();
        req.setAttribute("list", list);
        List<Department> departmentList = new DepartmentDAOImpl().findAll();
        req.setAttribute("departmentList", departmentList);
        
        req.getRequestDispatcher("/views/employees.jsp").forward(req, resp);
    }
}

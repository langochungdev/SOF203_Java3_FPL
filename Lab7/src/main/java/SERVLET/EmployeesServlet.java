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

import DAO.DepartmentDAOImpl;
import DAO.EmployeeDAO;
import DAO.EmployeeDAOImpl;
import ENTITY.Employee;
import ENTITY.Department;

@WebServlet({ "/employee/index", "/employee/edit/*", "/employee/create", "/employee/update", "/employee/delete", "/employee/reset" })
public class EmployeesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Employee form = new Employee();
        try {
            BeanUtils.populate(form, req.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        EmployeeDAO dao = new EmployeeDAOImpl();
        String path = req.getServletPath();
        
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

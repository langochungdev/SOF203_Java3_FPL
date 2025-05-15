package testServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({
    "/crud/create",
    "/crud/update",
    "/crud/delete",
    "/crud/edit/*"
})
public class b4 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
    		resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String uri = req.getRequestURI();
        if (uri.contains("/edit/")) {
            String id = req.getPathInfo();
            out.println("<h3>Chỉnh sửa với ID = " + id + "</h3>");
        }
        else if (uri.endsWith("/create")) {
            out.println("<h3>Tạo mới</h3>");
        } else if (uri.endsWith("/update")) {
            out.println("<h3>Cập nhật</h3>");
        } else if (uri.endsWith("/delete")) {
            out.println("<h3>Xóa</h3>");
        } 
    }
}

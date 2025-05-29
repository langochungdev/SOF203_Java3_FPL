package Servlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/b3_DocThamSo")
public class b3_DocThamSo extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/b3_DocThamSo.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");
        String married = req.getParameter("married");
        String nationality = req.getParameter("nationality");
        String[] hobbies = req.getParameterValues("hobby");
        String note = req.getParameter("note");
        
        System.out.println("Tên đăng nhập: " + username);
        System.out.println("Mật khẩu: " + password);
        System.out.println("Giới tính: " + gender);
        System.out.println("Đã có gia đình? " + (married != null ? "Có" : "Chưa"));
        System.out.println("Quốc tịch: " + nationality);
        System.out.print("Sở thích: ");
        if (hobbies != null){
            for(String h : hobbies){
                System.out.print(h + ", ");
            }
        }else{
            System.out.print("Không có");
        }
        System.out.println();

        System.out.println("Ghi chú: " + note);
	}

}

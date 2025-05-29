package Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({ "/index", "/reset", "/cong", "/tru", "/nhan", "/chia" })
public class ThemSL extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		if (uri.contains("/index")) {
			req.setAttribute("mess", "Nhập số và chọn phép tính");
		} else if (uri.contains("/reset")) {
			req.setAttribute("a", "");
			req.setAttribute("b", "");
			req.setAttribute("mess", "Đã làm mới form");
		} else {
			try {
				Double a = Double.valueOf(req.getParameter("a"));
				Double b = Double.valueOf(req.getParameter("b"));
				Double tong = null;
				String mess = "";
				if (uri.contains("/cong")) {
					tong = a + b;
					mess = "Kết quả cộng: " + tong;
				} else if (uri.contains("/tru")) {
					tong = a - b;
					mess = "Kết quả trừ: " + tong;
				} else if (uri.contains("/nhan")) {
					tong = a * b;
					mess = "Kết quả nhân: " + tong;
				} else if (uri.contains("/chia")) {
					if (b == 0) {
						mess = "Không thể chia cho 0";
					} else {
						tong = a / b;
						mess = "Kết quả chia: " + tong;
					}
				}
				req.setAttribute("a", a);
				req.setAttribute("b", b);
				req.setAttribute("mess", mess);
			} catch (NumberFormatException e) {
				req.setAttribute("mess", "Giá trị nhập không hợp lệ");
			}
		}
		req.getRequestDispatcher("/views/them.jsp").forward(req, resp);
	}

}

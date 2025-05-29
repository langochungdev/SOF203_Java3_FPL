package Controller;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/navigate")
public class navmain extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String page = request.getParameter("page");
        String includePage;
        
        switch (page != null ? page : "") {
            case "phapluat":
                includePage = "phapluat.jsp";
                break;
            case "thethao":
            	includePage = "thethao.jsp";
                break;
            case "vanhoa":
            	includePage = "vanhoa.jsp";
                break; 
            case "tintucchitiet":
                includePage = "tinchitiet.jsp";
                break;
            default:
                includePage = "trangchu.jsp";
                break;
        }
        
        request.setAttribute("includePage", includePage);
        request.getRequestDispatcher("/views/main.jsp").forward(request, response);
    }
}
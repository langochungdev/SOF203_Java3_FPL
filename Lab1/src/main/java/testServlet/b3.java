package testServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/url-info/*")
public class b3 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<h2>Thông tin URL:</h2>");
        out.println("<ul>");
        out.println("<li><strong>URL:</strong> " + request.getRequestURL() + "</li>");
        out.println("<li><strong>URI:</strong> " + request.getRequestURI() + "</li>");
        out.println("<li><strong>QueryString:</strong> " + request.getQueryString() + "</li>");
        out.println("<li><strong>ServletPath:</strong> " + request.getServletPath() + "</li>");
        out.println("<li><strong>ContextPath:</strong> " + request.getContextPath() + "</li>");
        out.println("<li><strong>PathInfo:</strong> " + request.getPathInfo() + "</li>");
        out.println("<li><strong>Method:</strong> " + request.getMethod() + "</li>");
        out.println("</ul>");
    }
}

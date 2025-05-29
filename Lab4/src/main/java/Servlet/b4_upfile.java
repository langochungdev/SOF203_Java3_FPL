package Servlet;
import java.io.File;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig()
@WebServlet("/b4_upfile")
public class b4_upfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/b4_upfile.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part photo = req.getPart("photo");
		String filename = photo.getSubmittedFileName();
			String path = req.getServletContext().getRealPath("/imgs");
			File f = new File(path);
			if (!f.exists()) f.mkdirs();
			photo.write(path + "/" + filename);
			
		req.setAttribute("path", path);
		req.setAttribute("filename", filename);
		req.getRequestDispatcher("/views/b4_upfile.jsp").forward(req, resp);
	}

}

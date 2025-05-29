package com.rs.controller.admin;


import com.rs.dao.CategoryDAO;
import com.rs.dao.ArticleDAO;
import com.rs.dao.UserDAO;
import com.rs.entity.Article;
import com.rs.entity.Category;
import com.rs.entity.User;
import com.rs.service.ArticleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Servlet implementation class NewsCrudServlet
 */
@WebServlet({"/admin/news","/admin/news/edit/*","/admin/news/blank","/admin/news/create","/admin/news/update","/admin/news/delete","/admin/news/reset","/admin/news/search"})
public class NewsCrudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Article article;
//	private static Users user;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsCrudServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Article> list;
		String path = request.getServletPath();

		if(path.contains("search") && !request.getParameter("search").isBlank()) {
            try {
                new ArticleService( request,response).searchEngine();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } else if(path.contains("edit") || path.contains("blank")) {
            try {
                new ArticleService(request,response).detailCrud();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if(path.endsWith("news")) {
			try {
				new ArticleService(request,response).listCrud();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            new ArticleService(request,response).articleIUD();
        } catch (SQLException | ClassNotFoundException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("path", "/admin/newsDetail.jsp");
		request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
	}
	

}

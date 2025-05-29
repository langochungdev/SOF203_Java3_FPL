package com.rs.service;

import com.rs.dao.CategoryDAO;
import com.rs.entity.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    List<Category> list;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    public CategoryService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        list = new ArrayList<Category>();
    }

    public void listPage() throws ServletException, SQLException {
            list = CategoryDAO.getAllCategories();
            request.setAttribute("list", list);
    }

    public void IUD() throws SQLException, ClassNotFoundException, IOException {
        String path = request.getServletPath();
        Category c;
        if(path.contains("insert")){
            c = new Category();
            c.setName(request.getParameter("newCate"));
            CategoryDAO.addCategory(c);
            response.sendRedirect(request.getContextPath() + "/admin/category");
        }
        else{
            String id = request.getPathInfo().substring(1);
            if(path.contains("delete")){
                CategoryDAO.deleteCategory(Integer.parseInt(id));
                response.sendRedirect(request.getContextPath() + "/admin/category");
            }
            else if(path.contains("update")){
                c = CategoryDAO.getCategoryById(Integer.parseInt(id));
                c.setName(request.getParameter("update"+id));
                CategoryDAO.updateCategory(c);
                response.sendRedirect(request.getContextPath() + "/admin/category");
            }
        }
    }
}

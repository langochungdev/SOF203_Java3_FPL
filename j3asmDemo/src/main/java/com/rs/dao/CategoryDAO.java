package com.rs.dao;

import com.rs.entity.Category;
import com.rs.util.other.XJdbc;

import java.sql.SQLException;
import java.util.List;

public class CategoryDAO {
//    private Connection connection;
//
//    public CategoryDAO(Connection connection) {
//        this.connection = connection;
//    }

    public static void addCategory(Category cate) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO CATEGORIES (Name) VALUES (?)";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setString(1, id);
//            stmt.setString(2, name);
//            stmt.executeUpdate();
//        }
        XJdbc.IUD(sql,cate.toInsertData());
    }

    public static void updateCategory(Category cate) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE CATEGORIES SET Name = ? WHERE Id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setString(1, name);
//            stmt.setString(2, id);
//            stmt.executeUpdate();
//        }
        XJdbc.IUD(sql, cate.toUpdateData());
    }

    public static void deleteCategory(long id) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE CATEGORIES SET Active = 0 WHERE Id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setString(1, id);
//            stmt.executeUpdate();
//        }
        XJdbc.IUD(sql, id);
    }
    
    public static Category getCategoryById(long id) {
    	String sql = "SELECT * FROM CATEGORIES WHERE Id=? and Active=1";

//        try (PreparedStatement stmt = connection.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                newsList.add(rs.getString("Title"));
//            }
//        }
        return XJdbc.getSingleResult(Category.class, sql, id);
    }

    public static List<Category> getAllCategories() {
    	String sql = "SELECT * FROM CATEGORIES order by Active desc";
        //        try (PreparedStatement stmt = connection.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                categories.add(rs.getString("Name"));
//            }
//        }
        return XJdbc.getResultList(Category.class, sql);
    }
}

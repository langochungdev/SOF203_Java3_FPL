package com.rs.dao;

import com.rs.entity.Article;
import com.rs.util.other.XJdbc;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ArticleDAO {
//    private Connection connection;
//
//    public NewsDAO(Connection connection) {
//        this.connection = connection;
//    }

	public static void addNews(Article article) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO NEWS (Title, Content, Image, PostedDate, Author, ViewCount, CategoryId, Home) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setString(1, id);
//            stmt.setString(2, title);
//            stmt.setString(3, content);
//            stmt.setString(4, image);
//            stmt.setDate(5, postedDate);
//            stmt.setString(6, author);
//            stmt.setInt(7, viewCount);
//            stmt.setString(8, categoryId);
//            stmt.setBoolean(9, home);
//            stmt.executeUpdate();
//        }
		XJdbc.IUD(sql, article.toInsertData());
	}

	public static void updateNews(Article article) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE NEWS SET Title = ?, Content = ?, Image = ?, PostedDate = ?, Author = ?, ViewCount = ?, CategoryId = ?, Home = ? WHERE Id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setString(1, title);
//            stmt.setString(2, content);
//            stmt.setString(3, image);
//            stmt.setDate(4, postedDate);
//            stmt.setString(5, author);
//            stmt.setInt(6, viewCount);
//            stmt.setString(7, categoryId);
//            stmt.setBoolean(8, home);
//            stmt.setString(9, id);
//            stmt.executeUpdate();
//        }
		XJdbc.IUD(sql, article.toUpdateData());
	}

	public static void deleteNews(long id) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE NEWS SET Home = 0, Active = 0 WHERE Id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setString(1, id);
//            stmt.executeUpdate();
//        }
		XJdbc.IUD(sql, id);
	}

	public static void noWayHome(long id) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE NEWS SET Home = 0 WHERE Id = ?";
		XJdbc.IUD(sql, id);
	}

	public static Article getNewsById(long id) throws SQLException {
		String sql = "SELECT * FROM NEWS WHERE Id=?";

//        try (PreparedStatement stmt = connection.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                newsList.add(rs.getString("Title"));
//            }
//        }
		return XJdbc.getSingleResult(Article.class, sql, id);
	}

	public static List<Article> getAllNews() throws SQLException {
		String sql = "SELECT * FROM NEWS order by Active desc";

//        try (PreparedStatement stmt = connection.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                newsList.add(rs.getString("Title"));
//            }
//        }
		return XJdbc.getResultList(Article.class, sql);
	}

	public static List<Article> getAllHomeNews() throws SQLException {
		String sql = "SELECT * FROM NEWS WHERE HOME = 1 LIMIT 5";

//        try (PreparedStatement stmt = connection.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                newsList.add(rs.getString("Title"));
//            }
//        }
		return XJdbc.getResultList(Article.class, sql);
	}

	public static List<Article> getAllNewsByAuthor(long authorID) throws SQLException {
		String sql = "SELECT * FROM NEWS WHERE Author = ?";

//        try (PreparedStatement stmt = connection.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                newsList.add(rs.getString("Title"));
//            }
//        }
		return XJdbc.getResultList(Article.class, sql, authorID);
	}

	public static List<Article> searchNews(String keyword) throws SQLException {
		String sql = "SELECT NEWS.*" +
				" FROM NEWS JOIN USERS ON NEWS.Author = USERS.Id JOIN CATEGORIES ON NEWS.CategoryId = CATEGORIES.Id " +
				"WHERE Title like ? or Content like ? or USERS.Fullname like ? or CATEGORIES.Name like ?";
        return XJdbc.getResultList(Article.class, sql, "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
	}
	
	public static List<Article> searchNewsByAuthor(long authorID, String keyword) throws SQLException {
		String sql = "SELECT NEWS.*" +
				" FROM NEWS JOIN USERS ON NEWS.Author = USERS.Id JOIN CATEGORIES ON NEWS.CategoryId = CATEGORIES.Id " +
				"WHERE USERS.Id = ? and ( Title like ? or Content like ? or USERS.Fullname like ? or CATEGORIES.Name like ? )";
		return XJdbc.getResultList(Article.class, sql, authorID, "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
	}

	public static List<Article> searchAll(String keyword) throws SQLException {
	    String sql = "SELECT DISTINCT NEWS.* " +
	                 "FROM NEWS " +
	                 "LEFT JOIN USERS ON NEWS.Author = USERS.Id " +
	                 "LEFT JOIN CATEGORIES ON NEWS.CategoryId = CATEGORIES.Id " +
	                 "WHERE NEWS.Title LIKE ? " +
	                 "OR NEWS.Content LIKE ? " +
	                 "OR USERS.Fullname LIKE ? " +
	                 "OR CATEGORIES.Name LIKE ?";

	    String searchKeyword = "%" + keyword + "%";
	    return XJdbc.getResultList(Article.class, sql, searchKeyword, searchKeyword, searchKeyword, searchKeyword);
	}

	public static List<Article> getNewsByCategory(String categoryName) throws SQLException {
		String sql = "SELECT NEWS.* FROM NEWS JOIN CATEGORIES ON NEWS.CategoryId = CATEGORIES.Id WHERE CATEGORIES.Name = ?";
        return XJdbc.getResultList(Article.class, sql, categoryName);
	}

	public static List<Article> getNewsByDateRange(Date startDate, Date endDate) throws SQLException {
		String sql = "SELECT * FROM NEWS WHERE PostedDate BETWEEN ? AND ?";
        return XJdbc.getResultList(Article.class, sql, startDate, endDate);
	}
	
	public static List<Article> getLatestNews() throws SQLException {
	    String sql = "SELECT * FROM NEWS ORDER BY PostedDate DESC limit 5";
        return XJdbc.getResultList(Article.class, sql);
	}

	public static List<Article> getTopNewsByViews() throws SQLException {
		String sql = "SELECT * FROM NEWS ORDER BY ViewCount DESC limit 5";
        return XJdbc.getResultList(Article.class, sql);
	}

	

	public static List<Article> getRelatedNews(long categoryId, long newsId) throws SQLException {
		String sql = "SELECT * FROM News WHERE categoryId = ? AND id <> ? limit 5";
		return XJdbc.getResultList(Article.class, sql, categoryId, newsId);
	}

	public static long generateNewId() throws ClassNotFoundException, SQLException {
		String sql = "select count(*) from NEWS";
		return (long) XJdbc.getValue(sql) + 1;
	}
}

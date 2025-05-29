package com.rs.entity;

import com.rs.dao.UserDAO;
import com.rs.dao.CategoryDAO;
import com.rs.util.other.XDate;

import java.sql.SQLException;
import java.util.Date;

public class Article {
	private long id;
	private String title;
	private String content;
	private String image;
	private Date postedDate;
	private long author;
	private int viewCount;
	private long categoryId;
	private boolean home;
	private boolean active;

	public Article() {
		super();
	}

	public Article(long id, String title, String content, String image, Date postedDate, long author, int viewCount,
                   long categoryId, boolean home, boolean active) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.image = image;
		this.postedDate = postedDate;
		this.author = author;
		this.viewCount = viewCount;
		this.categoryId = categoryId;
		this.home = home;
        this.active = active;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPostedDate() {
		return XDate.toString(postedDate, XDate.VN_DATE_1);
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public long getAuthor() {
		return author;
	}

	public void setAuthor(long author) {
		this.author = author;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public boolean isHome() {
		return home;
	}

	public void setHome(boolean home) {
		this.home = home;
	}

	public String getRepId() {
		return "NW" + id;
	}
	
	public String getAuthorName() throws SQLException {
		return UserDAO.getUserById(author).getFullname();
	}
	
	public String getCategoryName() throws SQLException {
		return CategoryDAO.getCategoryById(categoryId).getName();
	}

	public Object[] toInsertData() {
        return new Object[]{ title, content, image, postedDate, author, viewCount, categoryId, home };
	}

	public Object[] toUpdateData() {
        return new Object[]{ title, content, image, postedDate, author, viewCount, categoryId, home, id };
	}
	
	public String getImagePath() {
	    return "/photo/" + this.image;
	}

	public String getExcerpt() {
	    // Lấy 100 ký tự đầu tiên của nội dung
	    return this.content.length() > 100 ? this.content.substring(0, 100) + "..." : this.content;
	}

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

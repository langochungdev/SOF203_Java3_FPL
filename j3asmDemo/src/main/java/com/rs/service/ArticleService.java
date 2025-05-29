package com.rs.service;

import com.rs.dao.ArticleDAO;
import com.rs.dao.CategoryDAO;
import com.rs.dao.UserDAO;
import com.rs.entity.Article;
import com.rs.entity.Category;
import com.rs.entity.User;
import com.rs.util.other.Academics;
import com.rs.util.other.Arguments;
import com.rs.util.other.XCookie;
import com.rs.util.other.XFile;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ArticleService {

    private List<Article> articleList;
    private List<Article> homePageList;
    private List<Article> latestList;
    private List<Article> mostViewedList;
    private List<Article> viewedList;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public ArticleService(HttpServletRequest request, HttpServletResponse response) {
        this.response = response;
        this.request = request;
        this.articleList = new ArrayList<>();
        this.homePageList = new ArrayList<>();
        this.latestList = new ArrayList<>();
        this.mostViewedList = new ArrayList<>();
        this.viewedList = new ArrayList<>();
    }

    public void homepage() throws SQLException {
        articleList = ArticleDAO.getAllHomeNews();
        homePageList = ArticleDAO.getAllHomeNews();
        latestList = ArticleDAO.getLatestNews();
        mostViewedList = ArticleDAO.getTopNewsByViews();
        viewedList = new ArrayList<>();

        String[] viewedIds;
        XCookie xCookie = new XCookie(request, response);
        viewedIds = xCookie.getValue("viewedArticles").split("C");

        for (String id : viewedIds) {
            if (id != null && !id.isBlank()) {
                Article article = ArticleDAO.getNewsById(Integer.parseInt(id));
                viewedList.add(article);
                if (viewedList.size() == 6) {
                    break;
                }
            }
        }

        request.setAttribute("newsList", articleList);
        request.setAttribute("homePageList", homePageList);
        request.setAttribute("latestList", latestList);
        request.setAttribute("mostViewedList", mostViewedList);
        request.setAttribute("viewedList", viewedList);
        request.setAttribute("view", "/user/home.jsp");
    }

    public void listPage() throws SQLException {
        String path = request.getServletPath();
        AtomicReference<String> category = new AtomicReference<>("");
        Academics.switchCaseFirst(true, null,
                new Arguments(path.contains("culture"), () -> category.set("Văn hoá")),
                new Arguments(path.contains("tech"), () -> category.set("Công nghệ")),
                new Arguments(path.contains("law"), () -> category.set("Pháp luật")),
                new Arguments(path.contains("sports"), () -> category.set("Thể thao")),
                new Arguments(path.contains("travel"), () -> category.set("Du lịch")));
        articleList = ArticleDAO.getNewsByCategory(category.get());
        request.setAttribute("articleList", articleList);
        request.setAttribute("category", category.get());
        request.setAttribute("view", "/user/newsList.jsp");
    }

    public void listCrud() throws SQLException {
        User user = (User) request.getSession().getAttribute("currUser");
        if (user.getRole()) {
            articleList = ArticleDAO.getAllNews();
            request.setAttribute("list", articleList);
        } else {
            articleList = ArticleDAO.getAllNewsByAuthor(user.getId());
            request.setAttribute("list", articleList);
        }
        request.setAttribute("path", "/admin/newsList.jsp");
    }

    public void detailPage() throws SQLException {
        String id = request.getPathInfo().substring(1);
        Article article = ArticleDAO.getNewsById(Integer.parseInt(id));
        request.setAttribute("article", article);

        XCookie xCookie = new XCookie(request, response);
        String viewedIds = xCookie.getValue("viewedArticles");

        if (viewedIds == null || viewedIds.isEmpty()) {
            viewedIds = String.valueOf(article.getId());
        } else if (!viewedIds.contains(article.getId() + "")) {
            viewedIds = article.getId() + "C" + viewedIds;
        }

        xCookie.create("viewedArticles", viewedIds, 60 * 60 * 24);

        List<Article> relatedNews = ArticleDAO.getRelatedNews(article.getCategoryId(), article.getId());
        request.setAttribute("relatedNewsList", relatedNews);
        request.setAttribute("view", "/user/newsDetail.jsp");
    }

    public void detailCrud() throws SQLException, ClassNotFoundException {
        Article article = null;
        if (request.getServletPath().contains("edit")) {
            String id = request.getPathInfo().substring(1);
            article = ArticleDAO.getNewsById(Integer.parseInt(id));
        } else if (request.getServletPath().contains("blank")) {
            article = new Article();
            article.setId(ArticleDAO.generateNewId());
            article.setHome(true);
        }
        request.setAttribute("news", article);
        List<Category> categories;
        categories = CategoryDAO.getAllCategories();
        request.setAttribute("categories", categories);
        request.setAttribute("path", "/admin/newsDetail.jsp");
    }

    public void articleIUD() throws ServletException, IOException, SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        DateTimeConverter dtc = new DateConverter(new Date());
        dtc.setPattern("MM/dd/yyyy");
        ConvertUtils.register(dtc, Date.class);
        String uri = request.getServletPath();
        Article article = new Article();
        if (uri.contains("create")) {
            articleCreate(article);
        } else if (uri.contains("update")) {
            articleUpdate(article);
        } else if (uri.contains("delete")) {
            articleDelete(article);
        } else if (uri.contains("reset")) {
            articleReset(article);
        }
    }

    private void articleCreate(Article article) throws ServletException, IOException, InvocationTargetException, IllegalAccessException, SQLException, ClassNotFoundException {
        BeanUtils.populate(article, request.getParameterMap());
        Part img = request.getPart("img");
        if (img != null && !img.getSubmittedFileName().isBlank()) {
            XFile.upload(request, img);
            article.setImage(img.getSubmittedFileName());
        }
        article.setPostedDate(new Date());
        article.setAuthor(((Article) request.getSession().getAttribute("currUser")).getId());
        article.setHome(request.getParameter("onHome") != null);
        ArticleDAO.addNews(article);
        request.setAttribute("article", article);
        request.setAttribute("action", "edit");
    }

    private void articleUpdate(Article article) throws ServletException, IOException, InvocationTargetException, IllegalAccessException, SQLException, ClassNotFoundException {
        BeanUtils.populate(article, request.getParameterMap());
        Part img = request.getPart("img");
        if (img != null && !img.getSubmittedFileName().isBlank()) {
            XFile.upload(request, img);
            article.setImage(img.getSubmittedFileName());
        }
        article.setPostedDate(new Date());
        article.setId(Integer.parseInt(request.getParameter("repId").substring(2)));
        article.setHome(request.getParameter("onHome") != null);
        ArticleDAO.updateNews(article);
        request.setAttribute("article", article);
        request.setAttribute("action", "edit");
    }

    private void articleDelete(Article article) throws SQLException, ClassNotFoundException {
        ArticleDAO.deleteNews(Integer.parseInt(request.getParameter("repId").substring(2)));
        article.setId(ArticleDAO.generateNewId());
        article.setHome(true);
        request.setAttribute("article", article);
        request.setAttribute("action", "edit");
    }

    private void articleReset(Article article) throws SQLException, ClassNotFoundException {
        article = new Article();
        article.setId(UserDAO.generateNewId());
        article.setHome(true);
        request.setAttribute("article", article);
        request.setAttribute("action", "create");
    }

    public void searchEngine() throws SQLException {
        String servlet = request.getServletPath();
        if (servlet.startsWith("/user")) {
            String searchQuery = request.getParameter("search");
            articleList = ArticleDAO.searchNews(searchQuery);
            request.setAttribute("articleList", articleList);
            request.setAttribute("view", "/user/newsList.jsp");
            request.setAttribute("newsList", articleList);
            request.setAttribute("view", "/user/newsList.jsp");
        } else if (servlet.startsWith("/admin")) {
            User user = (User) request.getSession().getAttribute("currUser");
            if (user.getRole()) {
                articleList = ArticleDAO.searchNews(request.getParameter("search"));
                request.setAttribute("list", articleList);
            } else {
                articleList = ArticleDAO.searchNewsByAuthor(user.getId(), request.getParameter("search"));
                request.setAttribute("list", articleList);
            }
            request.setAttribute("path", "/admin/newsList.jsp");
        }

    }
}

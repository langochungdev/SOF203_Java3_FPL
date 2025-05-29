package com.rs.service;

import com.rs.dao.NewsLetterDAO;
import com.rs.entity.Newsletter;
import com.rs.util.other.XMailer;
import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsLetterService {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private List<Newsletter> list;

    public NewsLetterService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        list = new ArrayList<>();
    }

    public void runCrud() throws SQLException, ClassNotFoundException, ServletException, IOException {
        String path = request.getServletPath();
        Newsletter form;
        list = NewsLetterDAO.getAllNewsletter();
        request.setAttribute("list", list);
        if (path.contains("edit")) {
            int index = Integer.parseInt(request.getPathInfo().substring(1));
            form = list.get(index - 1);
            request.setAttribute("item", form);
        } else if (path.contains("update")) {
            form = new Newsletter();
            form.setEmail(request.getParameter("email"));
            form.setEnabled(request.getParameter("enabled") != null);
            NewsLetterDAO.updateNewsletter(form);
            request.setAttribute("item", form);
        } else if (path.contains("delete")) {
            NewsLetterDAO.deleteNewsletter(request.getParameter("email"));
            form = new Newsletter();
            request.setAttribute("item", form);
        }
        request.setAttribute("path", "/admin/letter.jsp");
        request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
    }

    public void subscribe() throws IOException, ServletException, SQLException, ClassNotFoundException {
        String path = request.getServletPath();


        if (path.endsWith("subscribe")) {
            String email = request.getParameter("email");

            // Kiểm tra email
            if (email == null || email.isEmpty()) {
                request.setAttribute("textColor", "text-red");
                request.setAttribute("errorMess", "Email không hợp lệ");
                request.getRequestDispatcher("/user/home").forward(request, response);
                return;
            }
            Boolean check = NewsLetterDAO.checkEnabled(email);
            if (Boolean.TRUE.equals(check)) {
                request.setAttribute("textColor", "text-red");
                request.setAttribute("errorMess", "Email đã được đăng ký");
                request.getRequestDispatcher("/user/home").forward(request, response);
                return;
            }

            String key = generateConfirmKey();
            try {
                XMailer.send(email, "Mã xác nhận", key);
                request.getSession().setAttribute("confirmKey", key);
                request.getSession().setAttribute("newsLetter", email);
                request.setAttribute("formAction", "/letter/subscribe/confirm");
                request.getRequestDispatcher("/user/confirmEmail.jsp").forward(request, response);
            } catch (MessagingException e) {
                request.setAttribute("textColor", "text-red");
                request.setAttribute("errorMess", "Có lỗi xảy ra khi gửi email, hãy thử lại sau");
                request.getRequestDispatcher("/user/home").forward(request, response);
            }
        } else if (path.endsWith("confirm")) {
            String key = (String) request.getSession().getAttribute("confirmKey");
            String keyInput = request.getParameter("confirm");
            if(!key.equals(keyInput)) {
                request.setAttribute("errorMess", "Mã xác nhận không hợp lệ");
                request.setAttribute("formAction", "/letter/subscribe/confirm");
                request.getRequestDispatcher("/user/confirmEmail.jsp").forward(request, response);
                return;
            }
            request.getSession().setAttribute("confirmKey", null);
            String email = (String) request.getSession().getAttribute("newsLetter");

            if (email == null) {
                request.setAttribute("textColor", "text-red");
                request.setAttribute("errorMess", "Không tìm thấy email để xác nhận");
                request.getRequestDispatcher("/user/home").forward(request, response);
                return;
            }

            // Kiểm tra lại trạng thái email
            Boolean check = NewsLetterDAO.checkEnabled(email);

            if (check == null) {
                NewsLetterDAO.addNewsletter(new Newsletter(email, true));
            } else if (!check) {
                NewsLetterDAO.updateNewsletter(new Newsletter(email, true));
            }
            request.getSession().setAttribute("newsLetter", null);
            request.setAttribute("textColor", "text-green");
            request.setAttribute("message", "Đăng ký thành công");
            request.getRequestDispatcher("/user/home").forward(request, response);
        }
    }

    public List<Newsletter> getAllNewsletters() {
        return NewsLetterDAO.getAllNewsletter();
    }

    public Newsletter getNewsletterByIndex(int index) throws IndexOutOfBoundsException {
        List<Newsletter> list = getAllNewsletters();
        return list.get(index - 1);
    }

    public void updateNewsletter(Newsletter newsletter) throws ClassNotFoundException, SQLException {
        NewsLetterDAO.updateNewsletter(newsletter);
    }

    public void deleteNewsletter(String email) throws ClassNotFoundException, SQLException {
        NewsLetterDAO.deleteNewsletter(email);
    }

    public boolean isEmailSubscribed(String email) {
        return Boolean.TRUE.equals(NewsLetterDAO.checkEnabled(email));
    }

    public void addOrUpdateNewsletter(String email) throws SQLException, ClassNotFoundException {
        Boolean check = NewsLetterDAO.checkEnabled(email);
        if (check == null) {
            NewsLetterDAO.addNewsletter(new Newsletter(email, true));
        } else if (!check) {
            NewsLetterDAO.updateNewsletter(new Newsletter(email, true));
        }
    }

    public String generateConfirmKey() {
        String allowed = "qwertyuiopasdfghjklzxcvbnmMNBVCXZASDFGHJKLPOIUYTREWQ0123456789";
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            key.append(allowed.charAt((int) (Math.random() * allowed.length())));
        }
        return key.toString();
    }
}

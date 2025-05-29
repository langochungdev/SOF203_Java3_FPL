package com.rs.service;

import com.rs.dao.UserDAO;
import com.rs.entity.User;
import com.rs.util.encrypt.AES;
import com.rs.util.encrypt.PasswordUtil;
import com.rs.util.other.XCookie;
import com.rs.util.other.XFile;
import com.rs.util.other.XMailer;
import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserService {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private User user;
    private List<User> list;

    public UserService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        list = new ArrayList<>();
    }

    public void login() throws ServletException, IOException, SQLException {
        if (request.getMethod().equals("GET")) {
            XCookie xCookie = new XCookie(request, response);
            String id = xCookie.getValue("rememberMe");
            if (id != null && !id.isBlank()) {
                User user = UserDAO.getUserById(Integer.parseInt(id));
                request.getSession().setAttribute("currUser", user);
                response.sendRedirect(request.getContextPath() + "/user/home");
                return;
            }
            request.setAttribute("view", "/user/login.jsp");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else if (request.getMethod().equals("POST")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
//		ObjectUtils.isEmpty(email);
            // commons.lang3
            // length > 0, trim = isBlank
            if (email != null && password != null) {
                try {
                    User user = UserDAO.getUserByEmail(email);
                    if (user != null) {
                        if (PasswordUtil.checkPassword(password, user.getPassword())) {
                            request.getSession().setAttribute("currUser", user);
                            String rememberMe = request.getParameter("rememberMe");
                            if (rememberMe != null) {
                                XCookie xCookie = new XCookie(request, response);
                                xCookie.create("rememberMe", user.getId() + "", 24 * 60 * 60);
                            }
                            response.sendRedirect(request.getContextPath() + "/user/home");
                            return;
                        } else {
                            request.setAttribute("error", "Mật khẩu không đúng");
                            request.setAttribute("view", "/user/login.jsp");
                        }
                    } else {
                        request.setAttribute("error", "Email không tồn tại");
                        request.setAttribute("view", "/user/login.jsp");
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    request.setAttribute("error", "Có lỗi xảy ra");
                    request.setAttribute("view", "/user/login.jsp");
                }
            } else {
                request.setAttribute("error", "Vui lòng điền đầy đủ thông tin");
                request.setAttribute("view", "/user/login.jsp");
            }
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    public void list() throws SQLException {
        list = UserDAO.getAllUsers();
        request.setAttribute("list", list);
        request.setAttribute("path", "/admin/userList.jsp");
    }

    public void detail() throws SQLException, ClassNotFoundException {
        String uri = request.getServletPath();
        User user;
        if (uri.contains("edit")) {
            String id = request.getPathInfo().substring(1);
            user = UserDAO.getUserById(Integer.parseInt(id));
            request.setAttribute("item", user);
            request.setAttribute("action", "edit");

        } else if (uri.contains("blank")) {
            user = new User();
            user.setId(UserDAO.generateNewId());
            user.setRole(true);
            request.setAttribute("item", user);
            request.setAttribute("action", "create");
        }
        request.setAttribute("path", "/admin/userDetail.jsp");
    }

    public void runCrud() throws SQLException, ClassNotFoundException, IOException, InvocationTargetException, IllegalAccessException, ServletException {
        DateTimeConverter dtc = new DateConverter(new Date());
        dtc.setPattern("MM/dd/yyyy");
        ConvertUtils.register(dtc, Date.class);
        String uri = request.getServletPath();
        if (uri.contains("create")) {
            creatUser();
        } else if (uri.contains("update")) {
            updateUser();
        } else if (uri.contains("delete")) {
            deleteUser();
        } else if (uri.contains("reset")) {
            resetUser();
        }
        request.setAttribute("item", user);
    }

    private void creatUser() throws InvocationTargetException, IllegalAccessException, SQLException, ClassNotFoundException, ServletException, IOException {
        user = new User();
        Part avatar = request.getPart("avatar");
        XFile.upload(request, avatar);
        user.setAvatar(avatar.getSubmittedFileName());
        BeanUtils.populate(user, request.getParameterMap());
        UserDAO.addUser(user);
        request.setAttribute("action", "edit");
    }

    private void updateUser() throws SQLException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, ServletException, IOException {
        user = UserDAO.getUserById(Integer.parseInt(request.getParameter("repId").substring(2)));
        Part avatar = request.getPart("avatar");
        if (avatar != null && !avatar.getSubmittedFileName().isBlank()) {
            XFile.upload(request, avatar);
            user.setAvatar(avatar.getSubmittedFileName());
        }
        BeanUtils.populate(user, request.getParameterMap());
        UserDAO.updateUser(user);
        request.setAttribute("action", "edit");
    }

    private void deleteUser() throws SQLException, ClassNotFoundException {
        UserDAO.deleteUser(Integer.parseInt(request.getParameter("repId").substring(2)));
        user = new User();
        user.setId(UserDAO.generateNewId());
        user.setRole(false);
        request.setAttribute("action", "create");
    }

    private void resetUser() throws SQLException, ClassNotFoundException {
        user = new User();
        user.setRole(false);
        user.setId(UserDAO.generateNewId());
        request.setAttribute("action", "create");
    }

    public void changePass() throws SQLException, ClassNotFoundException {
        String currPass = request.getParameter("currPass");
        String newPass = request.getParameter("newPass");
        String confirmPass = request.getParameter("confirmPass");
        User currUser = (User) request.getSession().getAttribute("currUser");
        if (!PasswordUtil.checkPassword(currPass, currUser.getPassword())) {
            request.setAttribute("error", "Mật khẩu hiện tại không khớp");
            request.setAttribute("view", "/user/changePass.jsp");
        } else if (!newPass.equals(confirmPass)) {
            request.setAttribute("error", "Mật khẩu xác nhận không khớp");
            request.setAttribute("view", "/user/changePass.jsp");
        } else {
            currUser.setPassword(PasswordUtil.hashPassword(newPass));
            UserDAO.updateUser(currUser);
            request.getSession().setAttribute("currUser", null);
            request.setAttribute("view", "/user/login.jsp");
        }
    }

    public void forgetPass() throws SQLException, ClassNotFoundException, IOException, ServletException {
        String key;
        boolean isConfirm = (boolean) request.getSession().getAttribute("isConfirm");
        if (isConfirm) {
            String newPass = request.getParameter("newPassword");
            String confirmPass = request.getParameter("confirmPassword");
            if (newPass.equals(confirmPass)) {
                User temp = UserDAO.getUserById((int) request.getSession().getAttribute("passChangeId"));
                temp.setPassword(PasswordUtil.hashPassword(newPass));
                UserDAO.updateUser(temp);
                request.getSession().setAttribute("currUser", null);
                response.sendRedirect(request.getContextPath() + "/user/login");
            } else {
                request.setAttribute("error", "Mật khẩu không khớp");
                request.setAttribute("view", "/user/forgetPass.jsp");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else {
            String email = request.getParameter("email");
            User temp = UserDAO.getUserByEmail(email);
            if (temp != null) {
                key = generateConfirmKey();
                try {
                    XMailer.send(email, "Mã xác nhận", key);
                    request.getSession().setAttribute("confirmKey", key);
                    request.getSession().setAttribute("passChangeId", temp.getId());
                    request.setAttribute("formAction", "/user/forgetPass/confirm");
                    request.getRequestDispatcher("/user/confirmEmail.jsp").forward(request, response);
                } catch (MessagingException e) {
                    request.setAttribute("error", "Có lỗi xảy ra");
                    request.setAttribute("view", "/user/forgetPass.jsp");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void confirmEmail() throws IOException, ServletException {
        String inputKey = request.getParameter("confirm");
        String confirmKey = (String) request.getSession().getAttribute("confirmKey");
        if (inputKey.equals(confirmKey)) {
            request.getSession().setAttribute("confirmKey", null);
            request.getSession().setAttribute("isConfirm", true);
            response.sendRedirect(request.getContextPath() + "/user/forgetPass");
        } else {
            request.setAttribute("errorMess", "Mã xác nhận không hợp lệ");
            request.setAttribute("formAction", "/user/forgetPass/confirm");
            request.getRequestDispatcher("/user/confirmEmail.jsp").forward(request, response);
        }
    }

    public void register() throws ServletException, IOException, SQLException, ClassNotFoundException {
        // Lấy thông tin từ form đăng ký
        String path = request.getServletPath();
        if (path.endsWith("confirm")) {
            confirmRegister();
        } else {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            if (email != null && password != null) {
                User existingUser = UserDAO.getUserByEmail(email);
                if (existingUser != null) {
                    request.setAttribute("error", "Email đã tồn tại, vui lòng sử dụng email khác");
                    request.setAttribute("view", "/user/register.jsp");
                } else if (!password.equals(confirmPassword)) {
                    request.setAttribute("error", "Mật khẩu và mật khẩu xác nhận không khớp");
                    request.setAttribute("view", "/user/register.jsp");
                } else {
                    // Gửi mail cùng mã xác nhận
                    String confirmKey = generateConfirmKey();
                    try {
                        XMailer.send(email, "Mã xác nhận", confirmKey);
                        String AES_KEY = AES.generateSecretKey();
                        request.getSession().setAttribute("confirmKey", confirmKey);
                        request.getSession().setAttribute("regEmail", AES.encryptPassword(email, AES_KEY));
                        request.getSession().setAttribute("AES_KEY", AES_KEY);
                        request.getSession().setAttribute("regPassword", PasswordUtil.hashPassword(password));
                        request.setAttribute("formAction", "/register/confirm");
                        request.getRequestDispatcher("/user/confirmEmail.jsp").forward(request, response);
                        return;
                    } catch (MessagingException | NoSuchAlgorithmException | NoSuchPaddingException |
                             InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
                        request.setAttribute("error", "Có lỗi khi gửi mail");
                        request.setAttribute("view", "/user/register.jsp");
                        throw new RuntimeException(e);
                    }
                }
            } else {
                request.setAttribute("error", "Vui lòng điền đầy đủ thông tin");
                request.setAttribute("view", "/user/register.jsp");
            }
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    public void confirmRegister() throws IOException, ServletException {
        String confirmKeyInput = request.getParameter("confirm");
        String confirmKey = (String) request.getSession().getAttribute("confirmKey");
        String AES_KEY = (String) request.getSession().getAttribute("AES_KEY");
        if (confirmKeyInput.equals(confirmKey)) {
            // Mã hóa mật khẩu với SHA-256
            String regPassword = (String) request.getSession().getAttribute("regPassword");
            String regEmail;
            try {
                regEmail = AES.decryptPassword((String) request.getSession().getAttribute("regEmail"), AES_KEY);
                // Lưu thông tin người dùng vào cơ sở dữ liệu
                UserDAO.addUser(regEmail, regPassword, false);
            } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
                     | IllegalBlockSizeException | BadPaddingException | ClassNotFoundException | SQLException e) {
                // TODO Auto-generated catch block
                throw new ServletException(e);
            } finally {
                request.getSession().setAttribute("confirmKey", null);
                request.getSession().setAttribute("regEmail", null);
                request.getSession().setAttribute("regPassword", null);
                request.getSession().setAttribute("AES_KEY", null);
            }

            // Điều hướng đến trang đăng nhập thành công
            response.sendRedirect(request.getContextPath() + "/user/login");
        } else {
            request.setAttribute("errorMess", "Mã xác nhận sai, hãy nhập lại");
            request.getRequestDispatcher("/user/confirmEmail.jsp").forward(request, response);
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

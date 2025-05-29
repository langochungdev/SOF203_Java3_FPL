package com.rs.util.encrypt;

public class PasswordUtil {
	// Mã hóa mật khẩu với SHA-256 (hash + salt)
    public static String hashPassword(String password) {
        return SHA256.hashPassword(password);
    }

    // Kiểm tra mật khẩu đã nhập với mật khẩu trong DB
    public static boolean checkPassword(String inputPassword, String storedHashedPassword) {
        byte[] salt = SHA256.getSalt(storedHashedPassword);
        String hashedInputPassword = SHA256.hashPassword(inputPassword, salt);
        return storedHashedPassword.equals(hashedInputPassword);
    }
    public static void main(String[] args) {
    	 System.out.println(SHA256.hashPassword("1234"));
    	 // M2Egks4ZEJsZUvlTsdQ2bg==:cWQB3ODHHrGjIV3oML7l1MyJqGo96SfmYxdpIcHGlnY=
    	 System.out.println(checkPassword("123456789","HeKKlCqIhcuidngKb9NAhw==:pzAXq2Nuy/EKf6we1EhEgxK0vxWiME9WDOMolLyyNK0="));
	}
}

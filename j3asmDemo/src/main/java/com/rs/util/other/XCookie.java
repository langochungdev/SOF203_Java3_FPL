package com.rs.util.other;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Base64;

public class XCookie {
	HttpServletRequest request;
	HttpServletResponse response;
	
	public XCookie(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	public Cookie get(String name) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie: cookies) {
				if(cookie.getName().equalsIgnoreCase(name)) {
					return cookie;
				}
			}
		}
		return null;
	}
	
	public String getValue(String name) {
		Cookie cookie = get(name);
		if(cookie != null) {
			String value = cookie.getValue();
			byte[] bytes = Base64.getDecoder().decode(value);
			return new String(bytes);
		}
		return "";
	}
	
	public Cookie create(String name, String value, int expiry) {
		byte[] bytes = value.getBytes();
		String encodedValue = Base64.getEncoder().encodeToString(bytes);
		Cookie cookie = new Cookie(name, encodedValue);
		cookie.setMaxAge(expiry);
		cookie.setPath("/");
		response.addCookie(cookie);
		return cookie;
	}

	
	public void delete(String name) {
		this.create(name, "", 0);
	}
}

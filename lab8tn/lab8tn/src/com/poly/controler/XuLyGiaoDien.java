package com.poly.controler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.http11.filters.VoidInputFilter;
@WebServlet({"","/home/index","/home/about","/home/contact","/home/nut"})
//@WebServlet({"","/index","/about","/contact","/nut"})
public class XuLyGiaoDien extends HttpServlet 
{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub

	String uri=req.getRequestURI();
	//if(uri.contains("index") || uri.contains(""))
		if(uri.contains("index") )
	{
		req.setAttribute("view", "/views/home/trangchu.jsp");
		//req.getRequestDispatcher("/index.jsp").forward(req, resp);
		
	}
	else if(uri.contains("about"))
	{	
	
		req.setAttribute("view","/views/home/about.jsp");
		//req.getRequestDispatcher("/index.jsp").forward(req, resp);
	
	}
	else if(uri.contains("contact"))
		
	{	req.setAttribute("view", "/views/home/contact.jsp");
	//req.getRequestDispatcher("/index.jsp").forward(req, resp);
	
		
	}
	else if(uri.contains("nut"))
	{	
		req.setAttribute("view", "/views/home/nut.jsp");
		//req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	// dong nay phai de duoi de sta view truoc
	req.getRequestDispatcher("/index.jsp").forward(req, resp);
}


@Override
protected void service(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException
{
	String uri=req.getRequestURI();
	//if(uri.contains("index") || uri.contains(""))
		if(uri.contains("index") )
	{
		req.setAttribute("view", "/views/home/trangchu.jsp");
		//req.getRequestDispatcher("/index.jsp").forward(req, resp);
		
	}
	else if(uri.contains("about"))
	{	
	
		req.setAttribute("view","/views/home/about.jsp");
		//req.getRequestDispatcher("/index.jsp").forward(req, resp);
	
	}
	else if(uri.contains("contact"))
		
	{	req.setAttribute("view", "/views/home/contact.jsp");
	//req.getRequestDispatcher("/index.jsp").forward(req, resp);
	
		
	}
	else if(uri.contains("nut"))
	{	
		req.setAttribute("view", "/views/home/nut.jsp");
		//req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	// dong nay phai de duoi de sta view truoc
	req.getRequestDispatcher("/index.jsp").forward(req, resp);
}

}

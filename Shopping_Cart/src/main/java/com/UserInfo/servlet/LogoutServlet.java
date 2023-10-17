package com.UserInfo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/log-out")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		
		try
		{
			if(request.getSession().getAttribute("auth")!= null)
			{
				request.getSession().removeAttribute("auth");
				response.sendRedirect("login.jsp");
			}
			else
			{
				response.sendRedirect("index.jsp");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}

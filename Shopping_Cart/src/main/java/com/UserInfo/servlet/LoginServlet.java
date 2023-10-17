package com.UserInfo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.UserInfo.connection.DbCon;
import com.UserInfo.dao.UserDao;
import com.UserInfo.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
   
	
			protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
			{
				response.sendRedirect("login.jsp");
			}

			
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
			{
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				String email = request.getParameter("login-email");
				String password  = request.getParameter("login-password");
				
				try
				{
					UserDao udao = new UserDao(DbCon.getConnection()); 
					User user = udao.userLogin(email, password);
					
					if(user!= null)
					{
						request.getSession().setAttribute("auth", user);
						response.sendRedirect("index.jsp");
					}
					else
					{
						
					}
				
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				
				
			}
	}


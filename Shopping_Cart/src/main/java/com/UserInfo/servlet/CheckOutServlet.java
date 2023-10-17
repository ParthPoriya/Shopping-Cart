package com.UserInfo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.UserInfo.connection.DbCon;
import com.UserInfo.dao.OrderDao;
import com.UserInfo.model.Cart;
import com.UserInfo.model.Order;
import com.UserInfo.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cart-check-out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        
        ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
        User auth = (User) request.getSession().getAttribute("auth");
        
        
        try
        {
        	 if(cart_list != null && auth != null)
             {
             	for(Cart c: cart_list)
             	{
             		Order order = new Order();
             		order.setId(c.getId());
             		order.setUid(auth.getId());
             		order.setQuantity(c.getQuantity());
             		order.setDate(formatter.format(date));
             		
             		OrderDao oDao = new OrderDao(DbCon.getConnection());
             		boolean result = oDao.insertOrder(order);
             		
             		if(!result)
             		{
             			break;
             		}
             	}
             	
             	cart_list.clear();
             	response.sendRedirect("orders.jsp");
             }
             else
             {
             	if(auth == null)
             	{
             		response.sendRedirect("login.jsp");
             	}
             	else
             	{
             		response.sendRedirect("cart.jsp");
             		
             	}
             }
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        
        
	} 


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		doGet(request, response);
	}

}

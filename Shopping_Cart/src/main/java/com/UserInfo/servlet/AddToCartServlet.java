package com.UserInfo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.UserInfo.model.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try
		{
			ArrayList<Cart> cartList = new ArrayList<Cart>();
			int id = Integer.parseInt(request.getParameter("id"));
			
			Cart cm = new Cart();
			cm.setId(id);
			cm.setQuantity(1);
			
			HttpSession session = request.getSession();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
			
			if(cart_list == null)
			{
				cartList.add(cm);
				session.setAttribute("cart-list",cartList);
				response.sendRedirect("index.jsp");
			}
			else
			{
				cartList = cart_list;
				
				boolean exist = false;
				
				
				for(Cart c : cart_list)
				{
					if(c.getId() == id)
					{
						exist = true;
						out.print("<h3 style='color:crimson; text-align:center'>Item already exist is Cart.<a href='cart.jsp'>Go to Cart Page</a></h3>");
					}
				}
				
				if(!exist)
				{
					cartList.add(cm);
					response.sendRedirect("index.jsp");
				}
			}
			
		}
		catch(Exception e)
		{
			
		}
	}

}

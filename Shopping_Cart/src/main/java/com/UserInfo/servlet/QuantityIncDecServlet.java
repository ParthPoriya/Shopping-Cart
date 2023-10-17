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

@WebServlet("/quantity-inc-dec")
public class QuantityIncDecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html;charset=UTF-8");
		
		String action = request.getParameter("action");
		int id = Integer.parseInt(request.getParameter("id"));
		
		ArrayList<Cart>  cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
		
		if(action != null & id >=0)
		{
			if(action.equals("inc"))
			{
				for(Cart c: cart_list)
				{
					if(c.getId() == id)
					{
						int quantity = c.getQuantity();
						quantity++;
						c.setQuantity(quantity);
						response.sendRedirect("cart.jsp");
					}
				}
			}
			
			
			if(action.equals("dec"))
			{
				for(Cart c: cart_list)
				{
					if(c.getId() == id && c.getQuantity() > 1)
					{
						int quantity = c.getQuantity();
						quantity--;
						c.setQuantity(quantity);
						break;
					}
				}
				response.sendRedirect("cart.jsp");
			}
			
		}
		else
		{
			response.sendRedirect("cart.jsp");
		}
		
		
			
	}

}

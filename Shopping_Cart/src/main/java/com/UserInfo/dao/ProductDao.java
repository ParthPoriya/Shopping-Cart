package com.UserInfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.UserInfo.connection.DbCon;
import com.UserInfo.model.Cart;
import com.UserInfo.model.Order;
import com.UserInfo.model.Product;

public class ProductDao 
{
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ProductDao(Connection con)
	{
		this.con = con;
	}
	
	public List<Product> getAllProducts()
	{
		List<Product> products = new ArrayList<Product>();
		
		try
		{
			Connection con = DbCon.getConnection();
			pst = con.prepareStatement("select * from products");
			rs = pst.executeQuery();
			
			while(rs.next())
			{
				Product row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				
				products.add(row);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return products;
	}
	
	public List<Cart> getCartProducts(ArrayList<Cart> cartList)
	{
		List<Cart> products = new ArrayList<Cart>();
		
		try
		{
			if(cartList.size() > 0)
			{
				for(Cart item : cartList)
				{
					con = DbCon.getConnection();
					pst = con.prepareStatement("select * from products where id= ?");
					pst.setInt(1, item.getId());
					
					rs = pst.executeQuery();
					
					while(rs.next())
					{
						Cart row = new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price")* item.getQuantity());
						row.setQuantity(item.getQuantity());
						
						products.add(row);
					}
					
				}
			}
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
		
		return products;
	}
	
	public double getTotalCartPrice(ArrayList<Cart> cartList)
	{
		double sum = 0;
		
		try
		{
			if(cartList.size() > 0)
			{
				for(Cart item : cartList)
				{
					con = DbCon.getConnection();
					pst = con.prepareStatement("select price from products where id = ?");
					pst.setInt(1, item.getId());
					
					rs = pst.executeQuery();
					
					while(rs.next())
					{
						sum+= rs.getDouble("price")*item.getQuantity();
					}
				}
			}
			
		}
		catch(Exception e)
		{
			
		}
		
		return sum;
	}
	
	public Product getSingleProduct(int id)
	{
		Product row = null;
		
		try
		{
			
			
			con = DbCon.getConnection();
			pst = con.prepareStatement("select * from products where id = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			while(rs.next())
			{
				
				row = new Product(); 
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return row;
	}
	
}

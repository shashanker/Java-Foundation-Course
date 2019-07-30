package com.epam.course.springmvc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.course.springmvc.entity.Purchases;
import com.epam.course.springmvc.entity.Stock;


@Component
public class PurchasesDAO {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	StockDAO stockDAO;
	
	public int save(Purchases purchase) {
		// TODO Auto-generated method stub
		Connection con = null;
		int i =0;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into purchases(stockId,quantity,value,user,status) values(?,?,?,?,?)");
			//ps.setLong(1, user.getId());
			ps.setLong(1, purchase.getStock().getId());
			ps.setLong(2, purchase.getQuantity());
			ps.setDouble(3, purchase.getValue());
			ps.setString(4, purchase.getUser());
			ps.setString(5, purchase.getStatus());
			i = ps.executeUpdate();
			ps.close();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return i;
	}

	public List<Purchases> findAllPurchasesByUser(String username) {
		// TODO Auto-generated method stub
		Connection con = null;
		List<Purchases> purchases = null;
		try {
			con = dataSource.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from purchases where user='"+username+"'");
			purchases = new ArrayList<>();
			while(rs.next())
			{
				Purchases purchase = new Purchases();
				purchase.setId(rs.getLong("id"));
				Stock stock = stockDAO.findById(rs.getLong("stockId"));
				purchase.setStock(stock);
				purchase.setQuantity(rs.getLong("quantity"));
				purchase.setStatus(rs.getString("status"));
				purchase.setValue(rs.getDouble("value"));
				/*System.out.println("ID = "+rs.getInt(1));
				System.out.println("username = "+rs.getString(2));*/
				purchases.add(purchase);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return purchases;
	}

	public int orderPurchase(long id) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = dataSource.getConnection();
			Statement st = con.createStatement();
			int i = st.executeUpdate("update purchases set status='Ordered' where id="+id);
			return i;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	public Purchases findById(long id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Connection con = null;
		Purchases purchase = null;
		try {
			con = dataSource.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from purchases where id="+id);
			while(rs.next())
			{
				purchase = new Purchases();
				purchase.setId(rs.getLong("id"));
				Stock stock = stockDAO.findById(rs.getLong("stockId"));
				purchase.setStock(stock);
				purchase.setQuantity(rs.getLong("quantity"));
				purchase.setStatus(rs.getString("status"));
				purchase.setValue(rs.getDouble("value"));
				/*System.out.println("ID = "+rs.getInt(1));
				System.out.println("username = "+rs.getString(2));*/
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return purchase;
	}

	public int removeById(long id) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = dataSource.getConnection();
			Statement st = con.createStatement();
			int i = st.executeUpdate("delete purchases where id="+id);
			return i;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	public int edit(Purchases purchase) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		try {
			
			Purchases oldPurchase = findById(purchase.getId());
			con = dataSource.getConnection();
			Statement st = con.createStatement();
			
			//int i = st.executeUpdate("delete purchases where id="+id);
			int i = st.executeUpdate("update purchases set quantity="+purchase.getQuantity()+",value="+oldPurchase.getStock().getPrice() * purchase.getQuantity() +" where id="+purchase.getId());
			return i;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
		
	}

}

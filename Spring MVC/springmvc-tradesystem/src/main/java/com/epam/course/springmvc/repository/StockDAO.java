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

import com.epam.course.springmvc.entity.Stock;

@Component
public class StockDAO {

	@Autowired
	DataSource dataSource;
	
	public void save(Stock stock) {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into stocks(name,description,price,image) values(?,?,?,?)");
			//ps.setLong(1, user.getId());
			ps.setString(1, stock.getName());
			ps.setString(2, stock.getDesc());
			ps.setDouble(3, stock.getPrice());
			ps.setString(4, stock.getImgUrl());
			ps.executeUpdate();
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
	}
	
	public List<Stock> getAllStocks() {
		// TODO Auto-generated method stub
		Connection con = null;
		List<Stock> stocks = new ArrayList<>();
		try {
			con = dataSource.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from stocks");
			while(rs.next())
			{
				Stock stock = new Stock();
				stock.setId(rs.getLong("id"));
				stock.setName(rs.getString("name"));
				stock.setDesc(rs.getString("description"));
				stock.setPrice(rs.getDouble("price"));
				stock.setImgUrl(rs.getString("image"));
				stocks.add(stock);
				/*System.out.println("ID = "+rs.getInt(1));
				System.out.println("username = "+rs.getString(2));*/
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stocks;
	}

	public Stock findById(Long id) {
		// TODO Auto-generated method stub
		Connection con = null;
		Stock stock = null;
		try {
			con = dataSource.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from stocks where id="+id);
			while(rs.next())
			{
				stock = new Stock();
				stock.setId(rs.getLong("id"));
				stock.setName(rs.getString("name"));
				stock.setDesc(rs.getString("description"));
				stock.setPrice(rs.getDouble("price"));
				stock.setImgUrl(rs.getString("image"));
				/*System.out.println("ID = "+rs.getInt(1));
				System.out.println("username = "+rs.getString(2));*/
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stock;
	}


}

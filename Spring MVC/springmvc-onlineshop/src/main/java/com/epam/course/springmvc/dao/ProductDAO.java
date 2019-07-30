package com.epam.course.springmvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.course.springmvc.model.Product;

@Repository
public class ProductDAO {
	
	@Autowired
	DataSource dataSource;
	
	public List<Product> getAllProducts() {
		Connection con = null;
		List<Product> items = null;
		try {
			con = dataSource.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from products");
			items = new ArrayList<>();

			while (rs.next()) {

				Product item = new Product();
				item.setId(rs.getInt(1));
				item.setName(rs.getString(2));
				item.setDesc(rs.getString(3));
				item.setPrice(rs.getDouble(4));
				item.setImgUrl(rs.getString(5));
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return items;
	}

	public Product getProductById(int id) {
		Connection con = null;
		Product product = null;
		try {
			con = dataSource.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from products where id=" + id);
			while (rs.next()) {
				product = new Product();
				product.setId(id);
				product.setName(rs.getString("name"));
				product.setDesc(rs.getString("description"));
				product.setPrice(rs.getDouble("price"));
				product.setImgUrl(rs.getString("image"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return product;
	}

}

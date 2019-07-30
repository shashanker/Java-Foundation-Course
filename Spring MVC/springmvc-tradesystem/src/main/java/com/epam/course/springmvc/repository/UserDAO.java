package com.epam.course.springmvc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.course.springmvc.entity.User;

@Component
public class UserDAO {
	
	@Autowired
	DataSource dataSource;

	public int  save(User user) {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into users(username,password,role,isUserActive) values(?,?,?,?)");
			//ps.setLong(1, user.getId());
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getRole());
			ps.setBoolean(4, user.getIsUserActive());
			int i = ps.executeUpdate();
			ps.close();
			findAllUsers();
			return i;
					
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
		return 0;
	}
	
	public void findAllUsers() {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from users");
			while(rs.next())
			{
				System.out.println("ID = "+rs.getInt(1));
				System.out.println("username = "+rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		Connection con = null;
		User user = null;
		try {
			con = dataSource.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from users where username='"+userName+"'");
			
			while(rs.next())
			{
				user = new User();
				user.setId(rs.getLong("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				user.setIsUserActive(rs.getBoolean("isUserActive"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}

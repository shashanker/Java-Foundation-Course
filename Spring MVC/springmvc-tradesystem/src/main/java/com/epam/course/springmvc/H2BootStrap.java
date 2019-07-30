package com.epam.course.springmvc;

import java.sql.Connection;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.course.springmvc.entity.Stock;
import com.epam.course.springmvc.entity.User;
import com.epam.course.springmvc.repository.StockDAO;
import com.epam.course.springmvc.repository.UserDAO;

/**
 * 
 * @author Shashanker_Vaduka
 *
 *         h2 boot strap. This class will pre-populate some data in the room
 *         table so that we have data to work with.
 */

@Component
public class H2BootStrap {

	@Autowired
	DataSource dataSource;

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	StockDAO stockDAO;

	@PostConstruct
	public void run() throws Exception {
		// TODO Auto-generated method stub

		System.out.println("Bootstrapping data : ");
		Connection con = null;
		con = dataSource.getConnection();
		Statement st = con.createStatement();
		/*try {
			st.executeUpdate("drop table users");
		} catch (Exception e) {
			// TODO: handle exception

			if (e.getMessage().indexOf("not found") != -1) {
				System.out.println(e.getMessage());

			}
		}*/
		st.executeUpdate("DROP TABLE IF EXISTS USERS");
		st.executeUpdate("DROP TABLE IF EXISTS STOCKS");
		
		System.out.println("---------Creating Table users------");
		st.execute("CREATE TABLE IF NOT EXISTS users( id bigint auto_increment NOT NULL, "
				+ "   username VARCHAR(250) NOT NULL,"
				+ "   password VARCHAR(250) NOT NULL,"
				+ "   role VARCHAR(10) NOT NULL," + " isUserActive BOOLEAN)");
		System.out.println("---------Table User created------");
		
		System.out.println("---------Creating Table Stocks------");
		st.execute("CREATE TABLE IF NOT EXISTS stocks( id bigint auto_increment NOT NULL, " + 
				"   name VARCHAR(250) NOT NULL," + 
				"   description VARCHAR(4000) NOT NULL," + 
				"   price DECIMAL(10,2) NOT NULL,"+
				" image VARCHAR(50))");
		System.out.println("---------Table Stocks created------");
		
		
		
		System.out.println("---------Creating Table Purchases------");
		st.execute("CREATE TABLE IF NOT EXISTS Purchases( id bigint auto_increment NOT NULL, " + 
				"   stockId bigint NOT NULL," + 
				"   quantity bigint NOT NULL," +
				"	value DECIMAL(10,2) NOT NULL,"+
				" 	user varchar(250) NOT NULL," +
				"   status varchar(30)"+
				" )");
		System.out.println("---------Table Purchases created------");
		
		User user = new User("shashanker", "vaduka", "USER", true);
		userDAO.save(user);
		Stock stock1 = new Stock("US", 100.0, "US Dollar", "dollar.jpg");
		Stock stock2 = new Stock("JAPAN", 69.76, "Japan Yen", "japan-yen.jpg");
		Stock stock3 = new Stock("EURO", 93.888, "European Currency", "euro.jpg");
		Stock stock4 = new Stock("INDIA", 1.0, "Indian Currency", "india.jpg");
		Stock stock5 = new Stock("UK", 120.50, "UK Pound", "pound.jpg");
		stockDAO.save(stock1);
		stockDAO.save(stock2);
		stockDAO.save(stock3);
		stockDAO.save(stock4);
		stockDAO.save(stock5);
		System.out.println("Bootstrapping data done: ");
	}

}

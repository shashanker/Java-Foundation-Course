package com.epam.course.springmvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Util {
	
	@Autowired
	DataSource dataSource;
	
	@PostConstruct
	public void init() {
		
		Connection con = null;
		try {
			 con = dataSource.getConnection();
			Statement st = con.createStatement();
			/*st.execute("create table items( id INT NOT NULL, " + 
					"   name VARCHAR(50) NOT NULL," + 
					"   description VARCHAR(20) NOT NULL," + 
					"   price DECIMAL(5,2))");*/
			try {
				st.executeUpdate("drop table products");
			}
			catch (Exception e) {
				// TODO: handle exception
				
				if(e.getMessage().indexOf("not found") != -1) {
					System.out.println(e.getMessage());
					
				}
			}
			System.out.println("---------Creating Table Items------");
			st.execute("create table products( id INT NOT NULL, " + 
					"   name VARCHAR(250) NOT NULL," + 
					"   description VARCHAR(4000) NOT NULL," + 
					"   price DECIMAL(5,2) NOT NULL,"+
					" image VARCHAR(50))");
			System.out.println("---------Table Products created------");
			
			/*st.executeUpdate("insert into items values(1,'abc','test description',10.222)");
			st.executeUpdate("insert into items values(2,'xyz','xyz description',40.222)");
			st.executeUpdate("insert into items values(3,'abbcc','abbcc description',50.222)");*/
			
			PreparedStatement ps = con.prepareStatement("insert into products values(?,?,?,?,?)");
			String name1 = "OnePlus 7 Pro (Nebula Blue, 8GB RAM, 256GB Storage)";
			String desc1 = "<li>Rear Camera|48MP (Primary)+ 8MP (Tele-photo)+16MP (ultrawide)| Front Camera|16 MP POP-UP Camera</li> <br/>\r\n" + 
					"<li>16.9 centimeters (6.67-inch) multi-touch capacitive touchscreen with 3120 x 1440 pixels resolution </li><br/>\r\n" + 
					"<li>Memory, Storage and SIM: 8GB RAM | 256GB internal memory | Dual SIM dual-standby (4G+4G) </li><br/>\r\n" + 
					"<li>Android Oxygen operating system with 2.84GHz Snapdragon 855 octa core processor </li><br/>\r\n" + 
					"<li>4000mAH lithium-ion battery, Buttons: Gestures and on-screen navigation support; Alert Slider </li><br/>\r\n" + 
					"<li>1 year manufacturer warranty for device and 6 months manufacturer warranty for in-box accessories including batteries from the date of purchase </li><br/>\r\n" + 
					"<li>Box also includes: Power Adapter, Type-C Cable (Support USB 2.0), Quick Start Guide, Welcome Letter, Safety Information and Warranty Card, Logo Sticker, Case, Screen Protector (pre-applied) and SIM Tray Ejector</li> <br/>";
			double price1 = 499.99;
			String img1 = "oneplus.jpg";
			
			ps.setInt(1, 1);
			ps.setString(2, name1);
			ps.setString(3, desc1);
			ps.setDouble(4, price1);
			ps.setString(5, img1);
			ps.executeUpdate();
			
			String name2 = "Men's Sneaker Casual Canvas Shoes-Grey";
			String desc2 = "<li>Sole: ethylene vinyl acetate </li><br/>\r\n" + 
					"<li>Sole: thermoplastic rubber | Closure: Lace-Up </li><br/>\r\n" + 
					"<li>Classic Fashionable Sneaker Showing Ultimate Trend ,Vogue And Comfort</li> <br/>\r\n" + 
					"<li>Material: Denim Jeans | Lifestyle: Casual</li><br/>\r\n" + 
					"<li>Light Weight And Durable, Optimal Flexibility, comfortable and stylish </li><br/>\r\n" + 
					"<li>Ultra thin layer of high abrasion rubber strategically placed on the out sole for targeted traction</li><br/>";
			double price2 = 249.99;
			String img2 = "shoe.jpg";
			
			ps.setInt(1, 2);
			ps.setString(2, name2);
			ps.setString(3, desc2);
			ps.setDouble(4, price2);
			ps.setString(5, img2);
			ps.executeUpdate();
			
			String name3 = "Canon EOS 1500D Digital SLR Camera ";
			String desc3 = "<li>Sensor: APS-C CMOS Sensor with 24.1 MP (high resolution for large prints and image cropping)</li><br/> \r\n" + 
					"<li>ISO: 100-6400 sensitivity range (critical for obtaining grain-free pictures, especially in low light)</li><br/>\r\n" + 
					"<li>Image Processor: DIGIC 4+ with 9 autofocus points (important for speed and accuracy of autofocus and burst photography)</li><br/>\r\n" + 
					"<li>Video Resolution: Full HD video with fully manual control and selectable frame rates (great for precision and high-quality video work)</li><br/>\r\n" + 
					"<li>Connectivity: WiFi, NFC and Bluetooth built-in (useful for remotely controlling your camera and transferring pictures wirelessly as you shoot)</li><br/>\r\n" + 
					"<li>Lens Mount: EF-S mount compatible with all EF and EF-S lenses (crop-sensor mount versatile and compact, especially when used with EF-S lenses)</li><br/>";
			double price3 = 729.99;
			String img3 = "canon.jpg";
			
			ps.setInt(1, 3);
			ps.setString(2, name3);
			ps.setString(3, desc3);
			ps.setDouble(4, price3);
			ps.setString(5, img3);
			
			ps.executeUpdate();
			
			System.out.println("-------Inserted data---------");
			
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

}

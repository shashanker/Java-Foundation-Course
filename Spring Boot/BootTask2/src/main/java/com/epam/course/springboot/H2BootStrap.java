package com.epam.course.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.epam.course.springboot.entity.Product;
import com.epam.course.springboot.repository.ProductRepository;

/**
 * 
 * @author Shashanker_Vaduka
 *
 *h2 boot strap. This class will pre-populate some data in the room table so that we have data to work with. 
 */

@Component
public class H2BootStrap implements CommandLineRunner{

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("Bootstrapping data : ");
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
		Product product = new Product();
		product.setName(name1);
		product.setDesc(desc1);
		product.setPrice(price1);
		product.setImgUrl(img1);
		productRepository.save(product);
		
		String name2 = "Men's Sneaker Casual Canvas Shoes-Grey";
		String desc2 = "<li>Sole: ethylene vinyl acetate </li><br/>\r\n" + 
				"<li>Sole: thermoplastic rubber | Closure: Lace-Up </li><br/>\r\n" + 
				"<li>Classic Fashionable Sneaker Showing Ultimate Trend ,Vogue And Comfort</li> <br/>\r\n" + 
				"<li>Material: Denim Jeans | Lifestyle: Casual</li><br/>\r\n" + 
				"<li>Light Weight And Durable, Optimal Flexibility, comfortable and stylish </li><br/>\r\n" + 
				"<li>Ultra thin layer of high abrasion rubber strategically placed on the out sole for targeted traction</li><br/>";
		double price2 = 249.99;
		String img2 = "shoe.jpg";
		
		Product product2 = new Product();
		product2.setName(name2);
		product2.setDesc(desc2);
		product2.setPrice(price2);
		product2.setImgUrl(img2);
		productRepository.save(product2);
		
		
		String name3 = "Canon EOS 1500D Digital SLR Camera ";
		String desc3 = "<li>Sensor: APS-C CMOS Sensor with 24.1 MP (high resolution for large prints and image cropping)</li><br/> \r\n" + 
				"<li>ISO: 100-6400 sensitivity range (critical for obtaining grain-free pictures, especially in low light)</li><br/>\r\n" + 
				"<li>Image Processor: DIGIC 4+ with 9 autofocus points (important for speed and accuracy of autofocus and burst photography)</li><br/>\r\n" + 
				"<li>Video Resolution: Full HD video with fully manual control and selectable frame rates (great for precision and high-quality video work)</li><br/>\r\n" + 
				"<li>Connectivity: WiFi, NFC and Bluetooth built-in (useful for remotely controlling your camera and transferring pictures wirelessly as you shoot)</li><br/>\r\n" + 
				"<li>Lens Mount: EF-S mount compatible with all EF and EF-S lenses (crop-sensor mount versatile and compact, especially when used with EF-S lenses)</li><br/>";
		double price3 = 729.99;
		String img3 = "canon.jpg";
		
		Product product3 = new Product();
		product3.setName(name3);
		product3.setDesc(desc3);
		product3.setPrice(price3);
		product3.setImgUrl(img3);
		productRepository.save(product3);
		System.out.println("Bootstrapping data done: ");
	}

}

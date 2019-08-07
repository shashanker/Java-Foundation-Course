package com.epam.course.springboot;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.epam.course.springboot.entity.Agent;
import com.epam.course.springboot.entity.Property;
import com.epam.course.springboot.enums.Status;
import com.epam.course.springboot.repository.AgentRepository;
import com.epam.course.springboot.repository.PropertyRepository;

/**
 * 
 * @author Shashanker_Vaduka
 *
 *h2 boot strap. This class will pre-populate some data in the room table so that we have data to work with. 
 */

@Component
@Order(2)
public class H2BootStrap implements CommandLineRunner{

	private static Logger log = Logger.getLogger(H2BootStrap.class.getName());
	
	@Autowired
	PropertyRepository propertyRepository;
	
	@Autowired
	AgentRepository agentRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		Agent agent = new Agent();
		agent.setName("Danielle Lazier");
		agent.setPhone("+54 356 647812");
		agent.setBio("Reared in the Tri-State Area (New York, New Jersey, Connecticut) and Dallas, Texas, "
				+ "I have an effective combination of Southern charm and Northeastern tenacity. "
				+ "I like to win (for my clients) but do so with a calm demeanor and a smile.");
		agent.setMobile("999 123 829 123");
		agent.setEmail("Danielle@example.com");
		agent.setSkype("Danielle.l");
		agent.setPic("agent-2.jpg");
		
		
		
		Property property = new Property();
		property.setTitle("property");
		property.setAddress1("204 Mount Olive Road Two");
		property.setAddress2("Doral,Florida 78345");
		property.setDesc("Before the very first basketball set their jaws grief and clinical care; "
				+ "Unfortunately, he will not, auctor sit amet aliquam vel, ullamcorper sit amet ligula. "
				+ "Reserved ultricies tomorrow, but the door was great. The classroom had not known how dui posuere blandit."
				+ " Mauris blandit feugiat elit tempus, dui eget tincidunt nibh pulvinar quam id posuere blandit. "
				+ "Chat arc was financing this layer and, at airline salad. Planning makeup until the lion need libero. "
				+ "Quisque velit nisi, pretium ut lacinia in, for that is an element. Till care employee libero.");
		property.setPrice(12000.0);
		property.setType("Rent");
		//property.setAgent(source.getAgent();
		property.setStatus(Status.OPEN);
		property.setArea(450l);
		property.setBeds(2l);
		property.setBaths(2l);
		property.setGarages(1l);
		property.setImgUrl("property-1.jpg");
		
		agent.addProperty(property);
		agentRepository.save(agent);
		property.setAgent(agent);
		propertyRepository.save(property);
		
		
		Agent agent2 = new Agent();
		agent2.setName("Margaret Stone Escala");
		agent2.setPhone("+54 356 945234");
		agent2.setBio("Sed porttitor lectus nibh. Praesent sapien massa, convallis a pellentesque nec,"
				+ " egestas non nisi. Vivamus suscipit tortor eget felis porttitor volutpat. "
				+ "Vivamus suscipit tortor eget felis porttitor volutpat.");
		agent2.setMobile("999 123 456 789");
		agent2.setEmail("agents@example.com");
		agent2.setSkype("Margaret.Es");
		agent2.setPic("agent-7.jpg");
		
		Property property2 = new Property();
		property2.setTitle("property");
		property2.setAddress1("204  Tempo Extreme Road Five");
		property2.setAddress2("Dacota,Florida 78565");
		property2.setDesc("Contrary to popular belief, Lorem Ipsum is not simply random text. "
				+ "It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old."
				+ " Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure"
				+ " Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in "
				+ "classical literature, "
				+ "discovered the undoubtable source");
		property2.setPrice(135000.0);
		property2.setType("BUY");
		//property.setAgent(source.getAgent();
		property2.setStatus(Status.OPEN);
		property2.setArea(450l);
		property2.setBeds(2l);
		property2.setBaths(2l);
		property2.setGarages(2l);
		property2.setImgUrl("property-3.jpg");
		agent2.addProperty(property2);
		agentRepository.save(agent2);
		property2.setAgent(agent2);
		propertyRepository.save(property2);
		
		
		Agent agent3 = new Agent();
		agent3.setName("Rene Rodriguez");
		agent3.setPhone("+54 356 941212");
		agent3.setBio("A high-touch broker known for his extensive market knowledge and his unmatched devotion to clients,"
				+ " Rene's success is based almost exclusively on positive referrals. He earns the respect of his clients by working"
				+ " tirelessly on their behalf and by always offering them candid advice.");
		agent3.setMobile("999 123 456 123");
		agent3.setEmail("agent2@example.com");
		agent3.setSkype("reneRod");
		agent3.setPic("agent-4.jpg");
		
		
		Property property3 = new Property();
		property3.setTitle("property");
		property3.setAddress1("204 Mount Olive Road Two");
		property3.setAddress2("Doral,Florida 78345");
		property3.setDesc("Before the very first basketball set their jaws grief and clinical care; "
				+ "Unfortunately, he will not, auctor sit amet aliquam vel, ullamcorper sit amet ligula. "
				+ "Reserved ultricies tomorrow, but the door was great. The classroom had not known how dui posuere blandit. "
				+ "Mauris blandit feugiat elit tempus, dui eget tincidunt nibh pulvinar quam id posuere blandit. "
				+ "Chat arc was financing this layer and, at airline salad. Planning makeup until the lion need libero. "
				+ "Quisque velit nisi, pretium ut lacinia in, for that is an element. "
				+ "Till care employee libero.");
		property3.setPrice(14500.0);
		property3.setType("Rent");
		//property.setAgent(source.getAgent();
		property3.setStatus(Status.OPEN);
		property3.setArea(350l);
		property3.setBeds(2l);
		property3.setBaths(1l);
		property3.setGarages(1l);
		property3.setImgUrl("property-10.jpg");
		agent3.addProperty(property3);
		agentRepository.save(agent3);
		property3.setAgent(agent3);
		propertyRepository.save(property3);
		
		log.info("H2BootStrap");
		
	}

}

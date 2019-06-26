package springcoretask5;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.course.springcore.config.AppConfig;
import com.epam.course.springcore.service.PositionService;
import com.epam.course.springcore.util.Util;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class)
public class AllTests {

	@Autowired
	ApplicationContext context;
	
	@Autowired
	PositionService positionService;
	
	 @BeforeClass
	    public  static void beforeAllTestMethods() {
	     
		 	
	        Util.initApp();
	    }
	
	@Test
	public void insertPositionTest() {
		//fail("Not yet implemented");
		int noOfPositions = positionService.getAvailablePositions().size();
		positionService.insertPosition("CEO");
		int uNoOfPositions = positionService.getAvailablePositions().size();
		
		assertTrue(uNoOfPositions == (noOfPositions+1));
	}

}

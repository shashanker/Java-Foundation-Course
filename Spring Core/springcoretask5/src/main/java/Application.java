import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epam.course.springcore.config.AppConfig;
import com.epam.course.springcore.util.Company;
import com.epam.course.springcore.util.Util;

public class Application {

	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		Util.initApp();
		Company company = context.getBean(Company.class);
		company.showOptions();
	}
}

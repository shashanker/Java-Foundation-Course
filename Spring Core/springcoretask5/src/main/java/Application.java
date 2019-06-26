import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epam.course.springcore.config.AppConfig;
import com.epam.course.springcore.registry.CompanyRegistry;
import com.epam.course.springcore.service.CompanyService;
import com.epam.course.springcore.util.Company;
import com.epam.course.springcore.util.Util;

public class Application {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		Util.initApp();
		Company company = context.getBean(Company.class);
		company.showOptions();

		/*
		 * Sample to show the factory-bean (service locator)
		 */
		System.out.println("Exited the Company App services");
		System.out.println("Below is sample demo for factory-bean (service locator)");
		CompanyRegistry companyRegistry = context.getBean(CompanyRegistry.class);
		System.out.println(
				"pick one of the service \n" + "\t EmployeeService \n" + "\t PositionService \n" + "\t SalaryService");
		Scanner in = new Scanner(System.in);
		String value = in.nextLine();
		CompanyService companyService = companyRegistry.getServiceBean(value);
		companyService.print();
	}
}

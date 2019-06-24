

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.course.springcore.beans.A;
import com.epam.course.springcore.beans.B;
import com.epam.course.springcore.beans.C;
import com.epam.course.springcore.beans.D;
import com.epam.course.springcore.beans.E;
import com.epam.course.springcore.beans.F;

public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		
		A a = (A)ctx.getBean("a");
		System.out.println(a);
		
		B b = ctx.getBean(B.class);
		System.out.println(b);
		
		
		/*
		 * Sample to check lookup-method injection
		 */
		C c1 = ctx.getBean(C.class);
		D d1 = c1.lookup();
		d1.setId(1);
		System.out.println("First D object = "+d1);
		
		C c2 = ctx.getBean(C.class);
		D d2 = c2.lookup();
		d2.setId(2);
		System.out.println("Second D object = "+d1);
		
		/*
		 * Method Replacement
		 */
		
		E e = ctx.getBean(E.class);
		e.print();
		
		F f = ctx.getBean(F.class);
		
		ctx.close();

	}
}

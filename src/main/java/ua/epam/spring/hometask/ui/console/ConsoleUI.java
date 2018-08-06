

package ua.epam.spring.hometask.ui.console;

import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.AuditoriumService;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.EventService;
import ua.epam.spring.hometask.service.UserService;
import ua.epam.spring.hometask.ui.console.state.MainState;
/**
 * @author Shashanker_Vaduka
 */
public class ConsoleUI {
	
	private ApplicationContext context;
	
	public static void main(String[] args) {
		
		ConsoleUI ui = new ConsoleUI();
		ui.initContext();
		ui.run();
		
	}
	
	


    private void initContext() {
		try {
			System.out.println("Loading Context");
			context = new ClassPathXmlApplicationContext("classpath:spring.xml");
			System.out.println("Context Loaded...");
		} catch (Exception e) {
			throw new IllegalStateException("please check the bean configurations", e);
		}
    }

    private void run() {
        System.out.println("Welcome to movie theater console service");
        
        

        MainState state = new MainState(context);

        state.run();

        System.out.println("Exiting.. Thank you.");
    }

  
}

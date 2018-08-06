package ua.epam.spring.hometask.serviceImpl;


/**
 * @author Shashanker_Vaduka
 */
import java.time.LocalDateTime;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

public abstract class DiscountStrategy {
	
	public abstract byte getDiscount(User user, Event event, LocalDateTime airDateTime, long numberOfTickets);

}

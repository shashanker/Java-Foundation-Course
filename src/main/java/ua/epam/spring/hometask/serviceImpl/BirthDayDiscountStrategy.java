package ua.epam.spring.hometask.serviceImpl;


/**
 * @author Shashanker_Vaduka
 */
import java.time.LocalDateTime;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

public class BirthDayDiscountStrategy extends DiscountStrategy{
	
	public byte getDiscount(User user, Event event, LocalDateTime airDateTime, long numberOfTickets)
	{
		if(airDateTime.getMonthValue() == user.getBirthDay().getMonth()+1)
		{
			int airDate = airDateTime.getDayOfMonth();
			user.getBirthDay().getDate();
			if(user.getBirthDay().getDate() - airDate <=5)
			{
				return 5;//discount percentage
			}
		}
		return 0;
	}

}

package ua.epam.spring.hometask.serviceImpl;


import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountService;

/**
 * @author Shashanker_Vaduka
 */
public class DiscountServiceImpl implements DiscountService {

	private List<DiscountStrategy> discountStrategy;

    /**
     * Getting discount based on some rules for user that buys some number of
     * tickets for the specific date time of the event
     * 
     * @param user
     *            User that buys tickets. Can be <code>null</code>
     * @param event
     *            Event that tickets are bought for
     * @param airDateTime
     *            The date and time event will be aired
     * @param numberOfTickets
     *            Number of tickets that user buys
     * @return discount value from 0 to 100
     */
	
	@Override
	public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
		// TODO Auto-generated method stub
		
		/*if(checkBirthdayDiscount(user, airDateTime))
		{
			return 5;
		}
		*/
		return 0;
	}

	public List<DiscountStrategy> getDiscountStrategy() {
		return discountStrategy;
	}

	public void setDiscountStrategy(List<DiscountStrategy> discountStrategy) {
		this.discountStrategy = discountStrategy;
	}
	
	
	
	
}

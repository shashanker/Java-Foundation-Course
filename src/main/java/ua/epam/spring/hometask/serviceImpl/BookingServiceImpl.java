package ua.epam.spring.hometask.serviceImpl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.BookingService;

/**
 * @author Shashanker_Vaduka
 */
public class BookingServiceImpl implements BookingService{
	
	Set<Ticket> bookedTickets = new HashSet<Ticket>();

    /**
     * Getting price when buying all supplied seats for particular event
     * 
     * @param event
     *            Event to get base ticket price, vip seats and other
     *            information
     * @param dateTime
     *            Date and time of event air
     * @param user
     *            User that buys ticket could be needed to calculate discount.
     *            Can be <code>null</code>
     * @param seats
     *            Set of seat numbers that user wants to buy
     * @return total price
     */
	@Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user,
            @Nonnull Set<Long> seats){
		
		Auditorium auditorium= event.getAuditoriums().get(dateTime);
		
		Set<Long> vipSeats = auditorium.getVipSeats();
		System.out.println("VipSeats ..."+vipSeats);
		
		vipSeats.retainAll(seats);
		
		System.out.println("VipSeats selected ..."+vipSeats);
		
		Set<Long> normalSeats = seats.stream().filter(seat -> !vipSeats.contains(seat)).collect(Collectors.toSet());
		
		System.out.println("Normal Seats ..."+normalSeats);
		
		double ticketPrice = 0.0;
		
		ticketPrice = (normalSeats.size() + vipSeats.size()*2)*event.getBasePrice();
		
		if(event.getRating().equals(EventRating.HIGH))
		{
			ticketPrice = 1.2 * ticketPrice;
		}
    	return ticketPrice;
    }

    /**
     * Books tickets in internal system. If user is not
     * <code>null</code> in a ticket then booked tickets are saved with it
     * 
     * @param tickets
     *            Set of tickets
     */
	@Override
    public void bookTickets(@Nonnull Set<Ticket> tickets){
		
		bookedTickets.addAll(tickets);
		
		
		
	}

    /**
     * Getting all purchased tickets for event on specific air date and time
     * 
     * @param event
     *            Event to get tickets for
     * @param dateTime
     *            Date and time of airing of event
     * @return set of all purchased tickets
     */
	@Override
    public @Nonnull Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime){
		System.out.println("Booking for Date .."+dateTime);
		
		return bookedTickets.stream().filter(t -> t.getEvent().getName().equals(event.getName())
				&& (t.getDateTime() == dateTime)).collect(Collectors.toSet());
    }

}

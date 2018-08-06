package ua.epam.spring.hometask.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.EventService;

/**
 * @author Shashanker_Vaduka
 */
public class EventServiceImpl implements EventService {
	
	private Set<Event> events = buildEventsSet();
	
	@Override
	public Event save(Event object) {
		// TODO Auto-generated method stub
		
		boolean result = events.add(object);
		if(result)
			System.out.println("Event Added .");
		else
			System.out.println("Event Updated .");
		return object;
	}

	@Override
	public void remove(Event object) {
		// TODO Auto-generated method stub
		boolean result = events.removeIf(u -> u.getId().equals(object.getId()));
		if(result)
			System.out.println("Event Deleted ...");
		else
			System.out.println("Event not available...");
	}

	@Override
	public Event getById(Long id) {
		// TODO Auto-generated method stub
		try{
			return events.stream().filter(u -> u.getId().equals(id)).findFirst().get();
		}
		catch(NoSuchElementException e)
		{
			System.err.println("No Event with that ID-"+id);
		}
		catch(Exception e)
		{
			System.err.println("Enter appropriate ID...");
		}
		
		return null;
		
	}

	@Override
	public Collection<Event> getAll() {
		// TODO Auto-generated method stub
		return events;
	}

    /**
     * Finding event by name
     * 
     * @param name
     *            Name of the event
     * @return found event or <code>null</code>
     */
	@Override
    public @Nullable Event getByName(@Nonnull String name){
		return events.stream().filter(u -> u.getName().equals(name)).findFirst().get();
    }

	

    /*
     * Finding all events that air on specified date range
     * 
     * @param from Start date
     * 
     * @param to End date inclusive
     * 
     * @return Set of events
     */
    // public @Nonnull Set<Event> getForDateRange(@Nonnull LocalDate from,
    // @Nonnull LocalDate to);

    /*
     * Return events from 'now' till the the specified date time
     * 
     * @param to End date time inclusive
     * s
     * @return Set of events
     */
    // public @Nonnull Set<Event> getNextEvents(@Nonnull LocalDateTime to);
	
	private Set<Event> buildEventsSet()
	{
		Event event = new Event();
        event.setId(Math.abs(UUID.randomUUID().getMostSignificantBits()));
        event.setName("Eve 1");
        event.setRating(EventRating.MID);
        event.setBasePrice(2.23);
        
        Set<Event> events = new HashSet<>();
        events.add(event);
        return events;
	}

}

package ua.epam.spring.hometask.serviceImpl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.AuditoriumService;

/**
 * @author Shashanker_Vaduka
 */
public class AuditoriumServiceImpl implements AuditoriumService {
	
	private Set<Auditorium> auditoriums = buildAuditoriumSet();

    /**
     * Getting all auditoriums from the system
     * 
     * @return set of all auditoriums
     */
	@Override
    public @Nonnull Set<Auditorium> getAll(){
    	return auditoriums;
    }

    /**
     * Finding auditorium by name
     * 
     * @param name
     *            Name of the auditorium
     * @return found auditorium or <code>null</code>
     */
	
	@Override
    public @Nullable Auditorium getByName(@Nonnull String name){
		try{
			return auditoriums.stream().filter(u -> u.getName().equals(name)).findFirst().get();
		}
		catch(Exception e)
		{
			System.err.println("Nothing Found " + e.getMessage());
		}
    	//return users.stream().filter(u -> u.getEmail().equals(email)).findFirst().get();
		return null;
	}

	private Set<Auditorium> buildAuditoriumSet()
	{
		Auditorium auditorium1 = new Auditorium();
		auditorium1.setName("auditorium1");
		auditorium1.setNumberOfSeats(500);
		Set<Long> vipSeats = new HashSet<Long>();
		vipSeats.add(Long.valueOf(100));
		vipSeats.add(Long.valueOf(200));
		vipSeats.add(Long.valueOf(300));
		vipSeats.add(Long.valueOf(400));
		vipSeats.add(Long.valueOf(500));
		auditorium1.setVipSeats(vipSeats);
		
		Auditorium auditorium2 = new Auditorium();
		auditorium2.setName("auditorium2");
		auditorium2.setNumberOfSeats(50);
		Set<Long> vipSeats2 = new HashSet<Long>();
		vipSeats2.add(Long.valueOf(10));
		vipSeats2.add(Long.valueOf(20));
		vipSeats2.add(Long.valueOf(30));
		vipSeats2.add(Long.valueOf(40));
		vipSeats2.add(Long.valueOf(50));
		auditorium2.setVipSeats(vipSeats2);
		
		
		Auditorium auditorium3 = new Auditorium();
		auditorium3.setName("auditorium3");
		auditorium3.setNumberOfSeats(100);
		Set<Long> vipSeats3 = new HashSet<Long>();
		vipSeats3.add(Long.valueOf(11));
		vipSeats3.add(Long.valueOf(22));
		vipSeats3.add(Long.valueOf(33));
		vipSeats3.add(Long.valueOf(44));
		vipSeats3.add(Long.valueOf(55));
		auditorium3.setVipSeats(vipSeats3);
		
		Set<Auditorium> auditoriums = new HashSet<Auditorium>();
		auditoriums.add(auditorium1);
		auditoriums.add(auditorium2);
		auditoriums.add(auditorium3);
		
		return auditoriums;
		
	}
}

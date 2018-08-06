package ua.epam.spring.hometask.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;

/**
 * @author Shashanker_Vaduka
 */
public class  UserServiceImpl implements UserService {
	
	
	private List<User> users = buildUsersList();

    /**
     * Finding user by email
     * 
     * @param email
     *            Email of the user
     * @return found user or <code>null</code>
     */
	@Override
    public @Nullable User getUserByEmail(@Nonnull String email){
		try{
			return users.stream().filter(u -> u.getEmail().equals(email)).findFirst().get();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
    	//return users.stream().filter(u -> u.getEmail().equals(email)).findFirst().get();
		return null;
    }

	@Override
	public User save(User object) {
		// TODO Auto-generated method stub
		boolean result = users.add(object);
		if(result)
			System.out.println("User Added .");
		else
			System.out.println("User Not added .");
		return object;
	}

	@Override
	public void remove(User object) {
		// TODO Auto-generated method stub
		
		boolean result = users.removeIf(u -> u.getId().equals(object.getId()));
		if(result)
			System.out.println("User Deleted ...");
		else
			System.out.println("User not available...");
		
	}

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub
		/*return users.stream().filter(u -> u.getId().longValue() == id.longValue()).findFirst().get();*/
		try
		{
			return users.stream().filter(u -> u.getId().equals(id)).findFirst().get();
		}
		catch(NoSuchElementException e)
		{
			System.err.println("No Value Present "+e);
		}
		return null;
	}

	@Override
	public Collection<User> getAll() {
		// TODO Auto-generated method stub
		return users;
	}
	
	private List<User> buildUsersList()
	{
		User user = new User();
		user.setId(Math.abs(UUID.randomUUID().getMostSignificantBits()));
		user.setFirstName("abc");
		user.setLastName("xyz");
		user.setEmail("abc_xyz@epam.com");
		List<User> users = new ArrayList<>();
		users.add(user);
		return users;
	}

}

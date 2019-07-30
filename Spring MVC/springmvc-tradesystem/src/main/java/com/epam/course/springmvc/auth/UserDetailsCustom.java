package com.epam.course.springmvc.auth;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.epam.course.springmvc.entity.User;


/**
 * This class provides user information encapsulated within a User object.
 * 
 * @author Suneel Ayyaparaju
 * 
 */
public class UserDetailsCustom implements UserDetails
{
    /**
     * Reference to serialVersionUID
     */
    private static final long serialVersionUID = 177633127773269469L;

    /**
     * Reference to the User object
     */
    private final User user;

    /**
     * @param user
     *            User object
     */
	public UserDetailsCustom(User user) {
		this.user = user;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		final List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
		if (user != null) {
				GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole());
				grantedAuthorityList.add(grantedAuthority);
			}
		return grantedAuthorityList;
	}

	public String getPassword() {
		return user.getPassword();
	}

	public String getUsername() {
		return user.getUsername();
	}

	public boolean isAccountNonExpired() {
		return isAccountNonLocked();
	}
    
	public boolean isAccountNonLocked() {
		if (user != null) {
			if (!user.getIsUserActive()) {
				return false;
			}
		}
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return isAccountNonLocked();
	}

	public boolean isEnabled() {
		return isAccountNonLocked();
	}

	public User getUser() {
		return user;
	}
}

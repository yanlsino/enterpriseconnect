package org.osforce.e2.web.custom.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.osforce.e2.service.system.UserService;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 19, 2011 - 3:10:22 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserService userService;
	private List<String> superUsernames = new ArrayList<String>();
	
	public UserDetailsServiceImpl() {
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setSuperUsernames(String superUsernamesStr) {
		String[] usernames = StringUtils.split(superUsernamesStr, ",");
		superUsernames = Arrays.asList(usernames);
	}
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		org.osforce.e2.entity.system.User user = userService.getUser(username);
		if(user==null) {
			throw new UsernameNotFoundException("User not found for username " + username);
		}
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
		if(superUsernames.contains(username)) {
			authorities.add(new GrantedAuthorityImpl("ROLE_SUPER"));
		}
		return new User(username, user.getPassword(), true, true, true,  true, authorities);
	}

}

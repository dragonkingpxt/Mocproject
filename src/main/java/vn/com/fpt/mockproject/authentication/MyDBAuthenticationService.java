package vn.com.fpt.mockproject.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.com.fpt.mockproject.dao.UserDAO;

@Service
public class MyDBAuthenticationService implements UserDetailsService {
	@Autowired
	private UserDAO userDAO;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		vn.com.fpt.mockproject.model.User userInfo = userDAO.getUser(username);
		System.out.println("UserInfo= " + userInfo);

		if (userInfo == null) {
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}

		// [USER,ADMIN,..]
		String role = userDAO.getUserGroup(username);

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (role != null) {
			// ROLE_USER, ROLE_ADMIN,..
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
			grantList.add(authority);
		}

		UserDetails userDetails = (UserDetails) new User(userInfo.getId(), //
				userInfo.getPassword(), grantList);

		return userDetails;
	}
}

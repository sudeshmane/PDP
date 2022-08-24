package com.example.library;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;
 
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
 
   // @Autowired
   // private UserRepository userRepository;
	static int inc = 0;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
      //  User user = userRepository.getUserByUsername(username);
        User user = new User(); 
        
        Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList();
        if(username.equals("1234")) {
        	user.setUsername("1234");
        	user.setPassword("pqr");
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN2");
        	grantedAuthoritiesList.add(grantedAuthority);
        	//user.getGrantedAuthoritiesList();
        	
        }
        else {
        	user.setUsername("abcd");
        	user.setPassword("password");
        	GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN3");
        	grantedAuthoritiesList.add(grantedAuthority);
        	//user.getGrantedAuthoritiesList();
        }
        user.setGrantedAuthoritiesList(grantedAuthoritiesList);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         inc ++;
        return new MyUserDetails(user);
    }

}

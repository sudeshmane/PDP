package com.example.library;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
 
public class UserDetailsServiceImpl implements UserDetailsService {
 
   // @Autowired
   // private UserRepository userRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
      //  User user = userRepository.getUserByUsername(username);
        User user = new User(); 
        Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ADMIN2");
        grantedAuthoritiesList.add(grantedAuthority);
        user.setGrantedAuthoritiesList(grantedAuthoritiesList);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new MyUserDetails(user);
    }

}

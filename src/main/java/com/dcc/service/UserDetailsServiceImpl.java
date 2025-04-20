package com.dcc.service;

import com.dcc.entity.User;
import com.dcc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByemail(email);
    	//User user = userRepository.findByuserName(username);
        //System.out.println(user.getUserName());
        if(user!=null){
            UserDetails userdetails = org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole().toString())//<-- this require comma(,)comma seperated values elipse(...) operator
//                    .roles(user.getRoles().toArray(new String[0]))//<-- this require comma(,)comma seperated values elipse(...) operator
                    .build();
            return  userdetails;
        }
        throw new UsernameNotFoundException("User not found with username :" + email);
    }
}

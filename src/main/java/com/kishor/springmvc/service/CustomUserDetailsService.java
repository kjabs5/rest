package com.kishor.springmvc.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kishor.springmvc.entity.User;
import com.kishor.springmvc.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);
	@Autowired
	UserRepository userRepository;
	
	@Override
	public  UserDetails loadUserByUsername(String username){
		
		User user=userRepository.findByUsername(username);
		return new MyUserDetails(user);
		//return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new ArrayList<>());
	}

}

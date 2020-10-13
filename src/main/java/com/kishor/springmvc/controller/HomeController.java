package com.kishor.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kishor.springmvc.entity.AuthenticationResponse;
import com.kishor.springmvc.entity.User;
import com.kishor.springmvc.filter.JwtFilter;
import com.kishor.springmvc.service.CustomUserDetailsService;
import com.kishor.springmvc.util.jwtUtil;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("/home")

public class HomeController {
	
	@Autowired
	private jwtUtil JwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService customUserDetailService;
	@Autowired
	private JwtFilter jwtFilter;
	
	@RequestMapping("/home")
	public String welcome() {
		return "welcome to spring rest";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<String> generateToken(@RequestBody AuthenticationResponse authenticationResponse) throws Exception
	//public String generateToken(@RequestBody User user) throws Exception
	{ 
		try 
	{
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationResponse.getUsername(),authenticationResponse.getPassword()));
	
	}
	catch(Exception ex)
	{
		throw new Exception("invalid username/password");
		
	}
		UserDetails userdetails=customUserDetailService.loadUserByUsername(authenticationResponse.getUsername());
		String jwt=JwtUtil.generateToken(userdetails);

	//return JwtUtil.generateToken(user.getUsername());
	return ResponseEntity.status(HttpStatus.OK).body(jwt);
	
	}

}

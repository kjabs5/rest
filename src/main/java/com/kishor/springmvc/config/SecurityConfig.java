package com.kishor.springmvc.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kishor.springmvc.filter.JwtFilter;
import com.kishor.springmvc.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	 private static final String[] AUTH_WHITELIST = {

	            // -- swagger ui
	            "/swagger-resources/**",
	            "/swagger-ui.html",
	            "/v2/api-docs",
	            "/webjars/**"
	    };
    
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	 @Autowired
	    private JwtFilter jwtFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     
		auth.userDetailsService(customUserDetailsService);
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	@Bean(name=BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		    .authorizeRequests()
		    .antMatchers("/api/product-list").hasAnyAuthority("admin","user")
		    .antMatchers("/api/viewproducts").permitAll()
		    .antMatchers("/home/authenticate").permitAll()
		    .antMatchers(AUTH_WHITELIST).permitAll()
		    .anyRequest().authenticated()
		    .and().exceptionHandling().and().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		
		
	}

}
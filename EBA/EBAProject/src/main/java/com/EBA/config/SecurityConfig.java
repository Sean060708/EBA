package com.EBA.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.EBA.Model.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	



	@Bean
    UserDetailsService userDetailsService() {
        return username -> myUserDetailsService.loadUserByUsername(username);
    }


//	    	@Bean
//		    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		    	http.authorizeHttpRequests(requests -> requests
//		    	.requestMatchers("/EBA","/test/hello").authenticated()
//		    	.anyRequest().permitAll()		
//		    	)
//		    	.formLogin(form -> form
//		    	.loginPage("/EBALogin")
//		    	.loginProcessingUrl("/login")
//		    	.permitAll()
//		    	)
//		    	.logout(logout -> logout
//		    	.permitAll()
//		    	.logoutSuccessUrl("/EBA")
//		    	)
//		    	.csrf(csrf -> csrf.disable());
//		    	
//		    	http.authenticationProvider(authenticationProvider());
//		    	return http.build();
//		    }
	        @Bean
	        DaoAuthenticationProvider authenticationProvider() {
	            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	            authProvider.setUserDetailsService(userDetailsService());
	            authProvider.setPasswordEncoder(passwordEncoder());
	            return authProvider;
	        }
	        
	        
	}
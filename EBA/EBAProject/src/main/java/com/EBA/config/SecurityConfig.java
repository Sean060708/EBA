package com.EBA.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.EBA.Model.MyUserDetailsService;
import com.EBA.fillter.JwtAuthenticationTokenFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private MyUserDetailsService myUserDetailsService;	
//		自訂義認證過濾器 進行jwt校驗
	@Autowired
	private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	



	@Bean
    UserDetailsService userDetailsService() {
        return username -> myUserDetailsService.loadUserByUsername(username);
    }



	        @Bean
	        DaoAuthenticationProvider authenticationProvider() {
	            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	            authProvider.setUserDetailsService(userDetailsService());
	            authProvider.setPasswordEncoder(passwordEncoder());
	            return authProvider;
	        }
	        @Bean
	        AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{
	        	return config.getAuthenticationManager();
	        }
	        
//	        登入請求配置
	        @Bean
	        SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	        	關閉csrf
	        	http.csrf(csrf -> csrf.disable());
//	        	配置請求的攔截方式
	        	http.authorizeHttpRequests(auth -> auth.requestMatchers("/login")
	        			.permitAll().anyRequest().authenticated());
//	        	配置過濾器執行順序
	        	http.addFilterBefore(jwtAuthenticationTokenFilter,UsernamePasswordAuthenticationFilter.class);
	        		return http.build();
	        	}
	}
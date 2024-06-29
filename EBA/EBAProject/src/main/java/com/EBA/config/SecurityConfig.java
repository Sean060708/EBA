package com.EBA.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties.ConfigureAction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.EBA.Model.MyUserDetailsService;
import com.EBA.fillter.JwtAuthenticationTokenFilter;
import com.EBA.handler.AnonymousAuthenticationHandler;
import com.EBA.handler.CustomerAccessDeniedHandler;
import com.EBA.handler.LoginFailureHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

//		自訂義認證過濾器 進行jwt校驗
	@Autowired
	private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
//	認證用戶無權限訪問資源的處理器
	@Autowired
	private CustomerAccessDeniedHandler customerAccessDeniedHandler;

	@Autowired
	private AnonymousAuthenticationHandler anonymousAuthenticationHandler;

	@Autowired
	private LoginFailureHandler loginFailureHandler;
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

//	        登入請求配置
			@Bean
	        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	        	關閉csrf
	        	http.csrf(csrf -> csrf.disable());
//	        	用戶認證校驗失敗處理器
	        	http.formLogin(configure -> configure.failureHandler(loginFailureHandler).loginPage("/EBALogin").loginProcessingUrl("/login").defaultSuccessUrl("/EBA", true).permitAll());
	        	http.sessionManagement(configure -> configure.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	        	//	        	配置請求的攔截方式
	        	http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
//	        	配置過濾器執行順序
	        	http.addFilterBefore(jwtAuthenticationTokenFilter,UsernamePasswordAuthenticationFilter.class);
//	        	添加自訂義的異常處理類
	        	http.exceptionHandling(t -> {t.authenticationEntryPoint(anonymousAuthenticationHandler).accessDeniedHandler(customerAccessDeniedHandler);
	        	});
	        	return http.build();
	        	}
}
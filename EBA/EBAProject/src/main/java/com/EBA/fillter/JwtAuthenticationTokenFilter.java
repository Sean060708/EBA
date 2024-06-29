package com.EBA.fillter;

import java.io.IOException;
import java.util.Objects;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.EBA.Model.LoginUser;
import com.EBA.Utils.JwtUtils;
import com.EBA.exception.CustomerAuthenticationException;
import com.EBA.handler.LoginFailureHandler;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//每一個servlet請求 只會執行一次 	
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter{
	@Autowired
	private LoginFailureHandler loginFailureHandler;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate; 
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = request.getHeader("Authorization");
		    System.out.println("Token: " + token);
			String uri = request.getRequestURI();
			System.out.println("uri" + uri);
			if(uri.equals("/hello")) {
	
//			如果是登入接口 直接放行
				System.out.println("Validating token...");
				this.validateToken(request);				
			}
		} catch (AuthenticationException e) {
			System.err.println("Authentication failed: " + e.getMessage());
			loginFailureHandler.onAuthenticationFailure(request, response, e);
		} 
//		放行	
		doFilter(request, response,filterChain);
	}
//	用於token的校驗方式
	private void validateToken(HttpServletRequest request) {
//		登入後再次請求其他需要認證的資源
		String uri = request.getRequestURI();
		System.out.println("進入校驗 : " + uri);
		String token = request.getHeader("Authorization");
	    System.out.println("Token from header: " + token);

		if(ObjectUtils.isEmpty(token)) { //Header中沒有token
			token = request.getParameter("Authorization");
			System.out.println("Token from parameter: " + token);
		}
		if(ObjectUtils.isEmpty(token)){
			throw new CustomerAuthenticationException("token為空");
		}
//		redis進行校驗
		String redisStr = stringRedisTemplate.opsForValue().get("token_" + token);
		if(ObjectUtils.isEmpty(redisStr)) {
			throw new CustomerAuthenticationException("token已過期");
		}
//		校驗令牌
		LoginUser loginUser = null;
		try {
			Claims claims = JwtUtils.parseJWT(token);
			String loginUserStr = claims.getSubject();
//			把字符串轉成loginUser對象
			loginUser = JSON.parseObject(loginUserStr,LoginUser.class);
			System.out.println(loginUser.toString());
			System.out.println("logingUser:" + loginUser.getUsername());
		} catch (Exception e) {
			System.err.println("Token validation failed: " + e.getMessage());
			throw new CustomerAuthenticationException("token校驗失敗");
		}
//		把驗證完的用戶訊息再放到SpringSecurity的上下文中
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		 System.out.println("Authentication set in SecurityContextHolder");

	}
	
}

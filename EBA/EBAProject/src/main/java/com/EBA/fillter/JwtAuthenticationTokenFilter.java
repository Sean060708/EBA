package com.EBA.fillter;

import java.io.IOException;

import javax.management.RuntimeErrorException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.EBA.Model.LoginUser;
import com.EBA.Utils.JwtUtils;
import com.alibaba.fastjson.JSON;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//每一個servlet請求 只會執行一次 	
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("123");
		String uri = request.getRequestURI();
		if(uri.equals("/login")) {
//			如果是登入接口 直接放行
			filterChain.doFilter(request, response);
			
			return;
		}
		String token = request.getHeader("Authorization");
		if(!StringUtils.hasText(token)) { //Header中沒有token
			throw new RuntimeException("token為空");
		}
//		校驗令牌
		LoginUser loginUser = null;
		try {
			Claims claims = JwtUtils.parseJWT(token);
			String loginUserStr = claims.getSubject();
//			把字符串轉成loginUser對象
			loginUser = JSON.parseObject(loginUserStr,LoginUser.class);
			System.out.println(loginUser.toString());
		} catch (Exception e) {
			throw new RuntimeException("token校驗失敗");
		}
//		把驗證完的用戶訊息再放到SpringSecurity的上下文中
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		System.out.println("over");
//		放行
		filterChain.doFilter(request, response);

	
	}
	
}

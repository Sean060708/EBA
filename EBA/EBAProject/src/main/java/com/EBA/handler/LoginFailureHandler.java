package com.EBA.handler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import javax.security.auth.login.AccountException;

import org.mybatis.logging.LoggerFactory;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.EBA.Model.R;
import com.EBA.exception.CustomerAuthenticationException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

//	用戶校驗失敗處理器
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		//		發生這個行為 做響應的處理
		response.setContentType("application/json;charset=utf-8");
//		構建輸出流對象
		ServletOutputStream outputStream = response.getOutputStream();
		String message = "";
		int code = 500;
		if(exception instanceof AccountExpiredException) {
			message = "帳號過期，登入失敗";
		}else if(exception instanceof BadCredentialsException) {
			message = "帳號或密碼輸入錯誤";
		}else if(exception instanceof CredentialsExpiredException) {
			message = "密碼過期，登入失敗";
		}else if(exception instanceof DisabledException) {
			message = "帳號已被禁止，登入失敗";
		}else if(exception instanceof LockedException) {
			message = "帳號被鎖，登入失敗";
		}else if(exception instanceof InternalAuthenticationServiceException) {
			message = "帳號不存在，登入失敗";
		}else if(exception instanceof CustomerAuthenticationException) {
			message = exception.getMessage();
			code = 600;
		}else {
			message = "登入失敗";
		}
//		調用這個fastjson 進行R對象的序列化
		String jsonString = JSON.toJSONString(R.error().message(message).code(code),SerializerFeature.DisableCircularReferenceDetect);
		outputStream.write(jsonString.getBytes(StandardCharsets.UTF_8));
		outputStream.flush();
		outputStream.close();
	}
	
}

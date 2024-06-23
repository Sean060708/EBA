package com.EBA.handler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.EBA.Model.R;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class AnonymousAuthenticationHandler implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8");
//		構建輸出流對象
		ServletOutputStream outputStream = response.getOutputStream();
//		調用這個fastjson 進行R對象的序列化
		
		String jsonString = "";
		if(authException instanceof BadCredentialsException) {
			jsonString = JSON.toJSONString(R.error().message("帳號或密碼錯誤").code(HttpServletResponse.SC_UNAUTHORIZED),SerializerFeature.DisableCircularReferenceDetect);
		}else if(authException instanceof InternalAuthenticationServiceException) {
			jsonString = JSON.toJSONString(R.error().message("無此帳號").code(HttpServletResponse.SC_UNAUTHORIZED),SerializerFeature.DisableCircularReferenceDetect);
		}else {
			jsonString = JSON.toJSONString(R.error().message("匿名用戶無權限訪問").code(600),SerializerFeature.DisableCircularReferenceDetect);
		}
		outputStream.write(jsonString.getBytes(StandardCharsets.UTF_8));
		outputStream.flush();
		outputStream.close();
	}

}

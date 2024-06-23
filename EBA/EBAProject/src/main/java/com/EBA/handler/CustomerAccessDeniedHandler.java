package com.EBA.handler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.EBA.Model.R;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//認證用戶無權限訪問的處理器
@Component
public class CustomerAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
//		發生這個行為 做響應的處理
		response.setContentType("application/json;charset=utf-8");
//		構建輸出流對象
		ServletOutputStream outputStream = response.getOutputStream();
//		調用這個fastjson 進行R對象的序列化
		String jsonString = JSON.toJSONString(R.error().message("無權限訪問，請聯繫管理員。").code(700),SerializerFeature.DisableCircularReferenceDetect);
		outputStream.write(jsonString.getBytes(StandardCharsets.UTF_8));
		outputStream.flush();
		outputStream.close();
	}
	
}

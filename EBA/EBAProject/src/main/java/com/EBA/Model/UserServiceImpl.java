package com.EBA.Model;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.EBA.Utils.JwtUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,Users> implements UserService {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@Override
	public String login(Users users) {
//		封裝auth對象
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(users.getUsername(),users.getPassword(),null);
//		進行校驗	
		Authentication authenticate = authenticationManager.authenticate(authentication);
		
		if(Objects.isNull(authenticate)) {
			throw new RuntimeException("登入失敗");
		}
//		如果通過
		LoginUser loginUser =(LoginUser)authenticate.getPrincipal();
//		生成JWT 使用fastJson方法
		String jsonString = JSON.toJSONString(loginUser);
//		調用JWT工具類 生成JWT令牌
		String jwt = JwtUtils.creatJWT(jsonString, null);
		return jwt;
	}
	
	
}
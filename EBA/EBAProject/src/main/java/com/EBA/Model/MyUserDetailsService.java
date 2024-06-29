package com.EBA.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.EBA.Mapper.MenuMapper;
import com.EBA.Mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import jakarta.servlet.http.HttpSession;


@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("用戶名:"+username);
		if(username.equals("")) {
			throw new InternalAuthenticationServiceException("");
		}
		//		查數據庫
		QueryWrapper<Users> wrapper = new QueryWrapper<Users>();
		wrapper.eq("username",username);
		Users users = userMapper.selectOne(wrapper);

		if(users == null) {
			throw new UsernameNotFoundException("");
		}
		session.setAttribute("users", users);
//		判斷密碼是不是正確
//		TODO
//		賦權操作 死的處理
		List<String> list = menuMapper.getMenuByUserId(users.getId());
//		返回UserDetails對象
		System.out.println("list" +list);
		list.forEach(System.out::println);
		return new LoginUser(users,list);
	}
	public UserDetails authenticate(String username, String rawPassword) throws UsernameNotFoundException {
        UserDetails userDetails = loadUserByUsername(username);
        if (!rawPassword.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("密碼不正確");
        }
        
        return userDetails;
    }
	
	
}

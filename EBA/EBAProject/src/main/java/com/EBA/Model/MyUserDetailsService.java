package com.EBA.Model;

import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		查數據庫
		QueryWrapper<Users> wrapper = new QueryWrapper<Users>();
		wrapper.eq("USERNAME",username);
		Users users = userMapper.selectOne(wrapper);

		if(Objects.isNull(users)) {
			throw new RuntimeException("帳號不存在");
		}
		
//		判斷密碼是不是正確
//		TODO
//		返回UserDetails對象

		return new LoginUser(users);
	}
	public UserDetails authenticate(String username, String rawPassword) throws UsernameNotFoundException {
        UserDetails userDetails = loadUserByUsername(username);

        if (!rawPassword.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("密碼不正確");
        }

        return userDetails;
    }
	
}

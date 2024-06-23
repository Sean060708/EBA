 package com.EBA.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.EBA.Model.R;
import com.EBA.Model.UserServiceImpl;
import com.EBA.Model.Users;
import com.EBA.exception.CustomerAuthenticationException;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class EBA {
	@Autowired
	private UserServiceImpl uService;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@GetMapping("/EBA")
	public String GoEBA() {
		
		return "EBA";
	}
	@GetMapping("/EBALogin")
	public String EBALogin() {
		
		
		return "EBALogin";
	}
	@PostMapping("/EBAlogin")
	@ResponseBody
	public R Login(@RequestBody Users users) {
		 Map<String, Object> map = uService.login(users);
		 if(!map.get("token").equals("") && !map.get("username").equals("")) {
			 return R.ok().message("登入成功").data("map",map);
		 }
		 return R.error().message("登入失敗");
	}
	
	@GetMapping("/hello")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('select')")  //權限本身就是使用字符串來管理
	public String hello() {
		return "Hello";
	}
	@GetMapping("/getNews")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('sys:news.select')")
	public String getNews() {
		return "news列表";
	}
	@PostMapping("/insertNews")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('sys:news.add')")
	public String insertNews() {
		return "新增成功";
	}
	@PutMapping("/updateNews")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('sys:news.update')")
	public String updateNews() {
		return "修改成功";
	}
	@DeleteMapping("/deleteNews")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('sys:news.delete')")
	public String deleteNews() {
		return "刪除成功";
	}
	
	@PostMapping("/EBAlogout")
	@ResponseBody
	public R logout(HttpServletRequest request,HttpServletResponse response) {
//		在logout中獲取jwt
		String token = request.getHeader("Authorization");
		if(ObjectUtils.isEmpty(token)) {
			token = request.getParameter("Authorization");
		}
		if(ObjectUtils.isEmpty(token)) {
			throw new CustomerAuthenticationException("token為空");
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
//			清除上下文
			new SecurityContextLogoutHandler().logout(request, response, authentication);
//			清除redis
			stringRedisTemplate.delete("token_" + token);
		}
		return R.ok().message("退出系統成功");
	}
}

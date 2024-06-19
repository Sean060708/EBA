package com.EBA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.EBA.Model.R;
import com.EBA.Model.UserServiceImpl;
import com.EBA.Model.Users;


@Controller
public class EBA {
	@Autowired
	private UserServiceImpl uService;
	
	
	@GetMapping("/EBA")
	public String GoEBA() {
		
		return "EBA";
	}
	@GetMapping("/EBALogin")
	public String EBALogin() {
		return "EBALogin";
	}
	@PostMapping("/login")
	@ResponseBody
	public R Login(@RequestBody Users users) {
		 String jwt = uService.login(users);
		 if(StringUtils.hasLength(jwt)) {
			 return R.ok().message("登入成功").data("token",jwt);
		 }
		 return R.error().message("登入失敗");
	}
	
	@GetMapping("/hello")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('select')")  //權限本身就是使用字符串來管理
	public String hello() {
		return "Hello";
	}
}

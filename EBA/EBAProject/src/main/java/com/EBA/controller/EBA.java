package com.EBA.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class EBA {
	
	@GetMapping("/EBA")
	public String GoEBA() {
		
		return "EBA";
	}
	@GetMapping("/EBALogin")
	public String EBALogin() {
		return "EBALogin";
	}
	@PostMapping("/test")
	@ResponseBody
	public String test(@RequestParam("username") String username,@RequestParam("password") String password,Model m) {

		return username + password;
	}
}

package com.EBA.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EBA {
	
	@GetMapping("/EBA")
	public String GoEBA() {
		
		return "EBA";
	}
}

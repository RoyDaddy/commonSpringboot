package com.neok.commonSpringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neok.commonSpringboot.service.TempService;

@Controller
public class TempController {
	@Autowired 
	private TempService tempService;
	@Autowired
	Environment env;
	
	@RequestMapping("/temp")
	public String temp() {
		env.getProperty("temp");
		return "/content/temp";
	}
}

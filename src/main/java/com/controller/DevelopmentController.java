package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.domain.Development;
import com.service.DevelopmentService;

@Controller
@RequestMapping("/dep")
public class DevelopmentController {

	@Autowired
	private DevelopmentService service;
	
	@RequestMapping("/execute")
	public String execute() {
		Development development=new Development();
		service.save(development);
		service.load(2);
		service.deleteById(2);
		service.findAll();
		
		
		return "finished";
	}
}

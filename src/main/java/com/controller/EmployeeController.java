package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.domain.Employee;
import com.service.EmployeeService;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

//	スコープの範囲がコントローラのクラス内
//	一度Autowiredすると他の@requestmappingの中で使う時もインスタンス化しなくていい
	@Autowired
	private EmployeeService service;
	
	@RequestMapping("/execute")
	public String execute() {
		Employee employee=new Employee();
		employee.setName("山岸 昂太郎");
		employee.setAge(23);
		employee.setGender("男");
		employee.setDevelopmentId(1);
		service.save(employee);
		
		Employee employee2=service.load(3);
		System.out.println(employee2);
		service.deleteById(3);
//		for-eachのメソッド参照  soutを行うとEmployeeクラスのtoStringが自動で呼び出される
		service.findAll().forEach(System.out::println);
		return "finished";
	}
}

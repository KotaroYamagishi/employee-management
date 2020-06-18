package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.Employee;
import com.repository.EmployeeRepository;

@Service
//serviceの中でトランザクションを設定しないと
@Transactional
@Component
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	public Employee load(Integer id) {
		return repository.load(id);
	}
	
	public List<Employee> findAll(){
		return repository.findAll();
	}
	
	public void save(Employee employee) {
	    repository.save(employee);
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
}

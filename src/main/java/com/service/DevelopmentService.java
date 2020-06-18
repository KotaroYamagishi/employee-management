package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.Development;
import com.repository.DevelopmentRepository;

@Service
@Component
@Transactional
public class DevelopmentService {

	@Autowired
	private DevelopmentRepository repository;
	
	public Development load(Integer id) {
		return repository.load(id);
	}
	
	public List<Development> findAll(){
		return repository.findAll();
	}
	
	public void save(Development development) {
		repository.save(development);
	}
	
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
}

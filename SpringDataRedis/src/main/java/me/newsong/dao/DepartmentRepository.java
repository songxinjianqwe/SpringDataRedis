package me.newsong.dao;

import org.springframework.data.repository.CrudRepository;

import me.newsong.domain.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer>{
	
}

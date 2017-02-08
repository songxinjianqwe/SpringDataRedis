package me.newsong.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import me.newsong.domain.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
	Page<Employee> findAll(Pageable pageable);
	List<Employee> findByLastName(String lastName);
}

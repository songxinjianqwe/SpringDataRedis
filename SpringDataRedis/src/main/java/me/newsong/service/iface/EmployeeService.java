package me.newsong.service.iface;

import org.springframework.data.domain.Page;

import me.newsong.domain.Employee;

public interface EmployeeService {
	Employee findByID(Integer id);
	void update(Employee employee);
	void delete(Integer id);
	void save(Employee employee);
	Page<Employee> findAll(int pageNum, int pageSize);
	boolean isLastNameValid(String lastName);
}

package me.newsong.service.iface;

import java.util.List;

import me.newsong.domain.Department;

public interface DepartmentService {
	List<Department> findAll();
	void save(Department department);
}

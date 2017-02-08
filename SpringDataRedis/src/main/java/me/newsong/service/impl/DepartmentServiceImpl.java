package me.newsong.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.newsong.dao.DepartmentRepository;
import me.newsong.domain.Department;
import me.newsong.service.iface.DepartmentService;
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService{
	@Autowired
	private DepartmentRepository dao;

	@Override
	public List<Department> findAll() {
		Iterator<Department> it = dao.findAll().iterator();
		List<Department> depts = new ArrayList<>();
		while(it.hasNext()){
			depts.add(it.next());
		}
		return depts;
	}

	@Override
	public void save(Department department) {
		dao.save(department);
	}
	
	
}

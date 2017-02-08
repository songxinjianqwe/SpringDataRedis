package me.newsong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.newsong.dao.EmployeeRepository;
import me.newsong.domain.Employee;
import me.newsong.service.iface.EmployeeService;
@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository dao;

	@Override
	public Employee findByID(Integer id) {
		return dao.findOne(id);
	}

	@Override
	public void update(Employee employee) {
		dao.save(employee);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public void save(Employee employee) {
		dao.save(employee);
	}

	@Override
	public Page<Employee> findAll(int pageNum, int pageSize) {
		return dao.findAll(new PageRequest(pageNum, pageSize));
	}

	@Override
	public boolean isLastNameValid(String lastName) {
		if(dao.findByLastName(lastName).size() == 0){
			return true;
		}
		return false;
	}

}

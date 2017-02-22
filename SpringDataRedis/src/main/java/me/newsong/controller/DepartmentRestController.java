package me.newsong.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import me.newsong.domain.Department;
import me.newsong.exceptions.DepartmentNotFoundException;
import me.newsong.service.iface.DepartmentService;

@CrossOrigin
@RestController
public class DepartmentRestController {
	@Autowired
	private DepartmentService departmentService;

	// 获得部门列表
	@RequestMapping(value = "/depts", method = RequestMethod.GET)
	public List<Department> findAllDepts(Locale locale) {
		List<Department> depts = departmentService.findAll();
		if (depts.size() == 0) {
			throw new DepartmentNotFoundException(locale);
		} else {
			return depts;
		}
	}
}

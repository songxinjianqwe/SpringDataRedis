package me.newsong.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.newsong.domain.Employee;
import me.newsong.exceptions.EmployeeNotFoundException;
import me.newsong.exceptions.UsernameExistedException;
import me.newsong.exceptions.ValidationException;
import me.newsong.service.iface.EmployeeService;

@CrossOrigin
@RestController
public class EmployeeRestController {
	@Autowired
	private EmployeeService employeeService;

	// 显示所有员工信息
	@RequestMapping(value = "/emps", method = RequestMethod.GET)
	public Page<Employee> findEmployeesByPage(
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") String pageNum) {
		int page = 0;
		try {
			page = Integer.parseInt(pageNum);
		} catch (NumberFormatException e) {
		}
		if (page < 0) {
			page = 0;
		}
		return employeeService.findAll(page, 5);
	}

	// 添加
	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	public void add(@RequestBody @Valid Employee employee, BindingResult result, Locale locale) {
		System.out.println(employee);
		if (!validateLastName(employee.getLastName())) {
			throw new UsernameExistedException(employee.getLastName(), locale);
		} else if (result.hasErrors()) {
			throw new ValidationException(result.getFieldErrors());
		}
		System.out.println("add:" + employee);
		employeeService.save(employee);
	}

	// 删除
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id, Locale locale) {
		if (employeeService.findByID(id) == null) {
			throw new EmployeeNotFoundException(locale);
		}
		System.out.println("delete:" + id);
		employeeService.delete(id);
	}

	// 更新
	@RequestMapping(value = "/emp", method = RequestMethod.PUT)
	public void update(@RequestBody @Valid Employee employee, BindingResult result, Locale locale) {
		if (!validateLastName(employee.getLastName())) {
			throw new UsernameExistedException(employee.getLastName(), locale);
		} else if (result.hasErrors()) {
			throw new ValidationException(result.getFieldErrors());
		}
		System.out.println("update:" + employee);
		employeeService.update(employee);
	}

	// 验证用户名是否合法
	@RequestMapping(value = "/emp/{lastName}", method = RequestMethod.GET)
	public boolean validateLastName(@PathVariable("lastName") String lastName) {
		if (employeeService.isLastNameValid(lastName)) {
			System.out.println("用户名可用");
			return true;
		} else {
			System.out.println("用户名不可用");
			return false;
		}
	}

}

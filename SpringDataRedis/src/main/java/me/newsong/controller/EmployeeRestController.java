package me.newsong.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.newsong.domain.Department;
import me.newsong.domain.Employee;
import me.newsong.service.iface.DepartmentService;
import me.newsong.service.iface.EmployeeService;

@RestController
public class EmployeeRestController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private DepartmentService departmentService;

	// 显示所有员工信息
	@RequestMapping(value = "/emps", method = RequestMethod.GET)
	public ResponseEntity<Page<Employee>> findEmployeesByPage(
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") String pageNum) {
		int page = 0;
		try {
			page = Integer.parseInt(pageNum);
		} catch (NumberFormatException e) {
		}
		if (page < 0) {
			page = 0;
		}
		return new ResponseEntity<>(employeeService.findAll(page, 5),HttpStatus.OK);
	}
	
	//获得部门列表
	@RequestMapping(value = "/depts", method = RequestMethod.GET)
	public ResponseEntity<List<Department>> findAllDepts() {
		List<Department> depts = departmentService.findAll();
		if (depts.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(depts, HttpStatus.OK);
		}
	}
	
	//添加
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public ResponseEntity<List<FieldError>> add(@RequestBody @Valid Employee employee, BindingResult result){
		if(!validateLastName(employee.getLastName())){
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}else if(result.hasErrors()){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error);
			}
			return new ResponseEntity<>(result.getFieldErrors(),HttpStatus.BAD_REQUEST);
		}
		System.out.println("add:"+employee);
		employeeService.save(employee);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//删除
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		if(employeeService.findByID(id) == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		System.out.println("delete:"+id);
		employeeService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//更新
	@RequestMapping(value = "/emp", method = RequestMethod.PUT)
	public ResponseEntity<List<FieldError>> update(@RequestBody @Valid Employee employee,BindingResult result) {
		if(!validateLastName(employee.getLastName())){
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}else if(result.hasErrors()){
			return new ResponseEntity<>(result.getFieldErrors(),HttpStatus.BAD_REQUEST);
		}
		System.out.println("update:"+employee);
		employeeService.update(employee);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//验证用户名是否合法
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

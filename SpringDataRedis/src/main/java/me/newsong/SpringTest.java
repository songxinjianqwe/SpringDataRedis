package me.newsong;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import me.newsong.domain.Department;
import me.newsong.domain.Employee;
import me.newsong.service.iface.DepartmentService;
import me.newsong.service.iface.EmployeeService;

public class SpringTest {
	private ApplicationContext ctx;
	private EmployeeService employeeService;
	private DepartmentService departmentService;
	
	@Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		employeeService = ctx.getBean(EmployeeService.class);
		departmentService = ctx.getBean(DepartmentService.class);
	}

	@Test
	public void test() {
		Department dept1 = new Department(1,"财务部");
		Department dept2 = new Department(2,"市场部");
		Department dept3 = new Department(3,"研发部");
		Department dept4 = new Department(4,"人事部");
		departmentService.save(dept1);
		departmentService.save(dept2);
		departmentService.save(dept3);
		departmentService.save(dept4);
		
		for (int i = 0; i < 26; ++i) {
			Department department = new Department();
			department.setId(i % 4 + 1);
			Employee employee = new Employee(i+1,(char) ('A' + i) + "", (char) ('A' + i) + "@163.com",
					i % 2 == 0 ? "M" : "F", department, new Date(), i * 1000);
			employeeService.save(employee);
		}
	}
	
	@Test
	public void testFind(){
		List<Department> depts = departmentService.findAll();
		System.out.println(depts);
	}
	
	@Test
	public void testFind2(){
//		Employee employee = employeeService.findByID(1);
//		System.out.println(employee);
//		System.out.println(employeeService.isLastNameValid("A"));
//		Page<Employee> employees = employeeService.findAll(0, 5);
//		System.out.println(employees.getContent());
		System.out.println(System.currentTimeMillis());
	}
}

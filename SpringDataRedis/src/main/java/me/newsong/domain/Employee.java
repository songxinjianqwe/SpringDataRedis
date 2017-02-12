package me.newsong.domain;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("employees")
public class Employee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6835815975637187630L;

	private Integer id;
	@NotEmpty
	@Indexed
	private String lastName;
	@Email
	private String email;
	private String gender;
	private Department dept;
	private Date birthday;
	private double salary;

	public Employee() {
	}

	public Employee(Integer id,String lastName, String email, String gender, Department dept, Date birthday, double salary) {
		this.id = id;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.dept = dept;
		this.birthday = birthday;
		this.salary = salary;
	}

	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", email=" + email + ", gender=" + gender + ", dept="
				+ dept + ", birthday=" + birthday + ", salary=" + salary + "]";
	}

}

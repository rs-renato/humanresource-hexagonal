package br.com.hrs.core.model;

import java.util.Date;
import java.util.Objects;

public class Employee {

	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private Date hireDate;
	private Float salary;
	private Float commissionPercent;
	private Employee manager;
	private Job job;
	private Department department;

	Employee(){
	}
	
	public Employee(Integer id, String firstName, String lastName, String email, String phone, Date hireDate, Float salary, Float commissionPercent, Employee manager, Job job, Department department) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.hireDate = hireDate;
		this.salary = salary;
		this.commissionPercent = commissionPercent;
		this.manager = manager;
		this.job = job;
		this.department = department;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public Float getCommissionPercent() {
		return commissionPercent;
	}

	public void setCommissionPercent(Float commissionPercent) {
		this.commissionPercent = commissionPercent;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return Objects.equals(id, employee.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	public static class Builder{

		private Employee employee = new Employee();

		public Builder id(Integer id) {
			this.employee.setId(id);
			return this;
		}

		public Builder firstName(String firstName) {
			this.employee.setFirstName(firstName);
			return this;
		}

		public Builder lastName(String lastName) {
			this.employee.setLastName(lastName);
			return this;
		}

		public Builder email(String email) {
			this.employee.setEmail(email);
			return this;
		}

		public Builder phone(String phone) {
			this.employee.setPhone(phone);
			return this;
		}

		public Builder hireDate(Date hireDate) {
			this.employee.setHireDate(hireDate);
			return this;
		}

		public Builder salary(Float salary) {
			this.employee.setSalary(salary);
			return this;
		}

		public Builder commissionPercent(Float commissionPercent) {
			this.employee.setCommissionPercent(commissionPercent);
			return this;
		}

		public Builder manager(Employee manager) {
			this.employee.setManager(manager);
			return this;
		}

		public Builder job(Job job) {
			this.employee.setJob(job);
			return this;
		}

		public Builder department(Department department) {
			this.employee.setDepartment(department);
			return this;
		}

		public Employee build(){
			return this.employee;
		}
	}
}
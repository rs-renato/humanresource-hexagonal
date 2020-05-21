package br.com.hrs.service.repository.jpa.entity;

import br.com.hrs.core.model.Employee;

import javax.persistence.*;
import java.util.Date;

@Entity(name="Employee")
@Table(name = "EMPLOYEES")
@Cacheable
public class EmployeeEntity extends Employee {

	protected EmployeeEntity(){
	}

	public EmployeeEntity(String firstName, String lastName, String email, String phone, Date hireDate, Float salary, Float commissionPercent, EmployeeEntity manager, JobEntity job, DepartmentEntity department) {
		super(null, firstName,lastName, email, phone, hireDate, salary, commissionPercent, manager, job, department);
	}

	public EmployeeEntity(Integer id, String firstName, String lastName, String email, String phone, Date hireDate, Float salary, Float commissionPercent, EmployeeEntity manager, JobEntity job, DepartmentEntity department) {
		super(id, firstName,lastName, email, phone, hireDate, salary, commissionPercent, manager, job, department);
	}

	/**
	 * @return Employee identifier.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_ID")
	public Integer getId() {
		return id;
	}
	
	/**
	 * @return Employee first name.
	 */
	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * @return Employee last name.
	 */
	@Column(name = "LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return Employee e-mail.
	 */
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}
	
	/**
	 * @return Employee phone.
	 */
	@Column(name = "PHONE_NUMBER")
	public String getPhone() {
		return phone;
	}

	/**
	 * @return Date the employee was hired.
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "HIRE_DATE")
	public Date getHireDate() {
		return hireDate;
	}
	
	/**
	 * @return Employee salary.
	 */
	@Column(name = "SALARY")
	public Float getSalary() {
		return salary;
	}
	
	/**
	 * @return Employee comission percent.
	 */
	@Column(name = "COMMISSION_PCT")
	public Float getCommissionPercent() {
		return commissionPercent;
	}
	
	/**
	 * @return Employee manager.
	 */
	@ManyToOne
	@JoinColumn(name = "MANAGER_ID")
	public EmployeeEntity getManager() {
		return (EmployeeEntity)manager;
	}
	
	/**
	 * @return Employee job.
	 */
	@ManyToOne
	@JoinColumn(name = "JOB_ID", nullable = false)
	public JobEntity getJob() {
		return (JobEntity)job;
	}

	/**
	 * @return Employee department.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPARTMENT_ID", nullable = false)
	public DepartmentEntity getDepartment() {
		return (DepartmentEntity)department;
	}

	/**
	 * @return Employee full name.
	 */
	@Transient
	public String getName() {
		return this.getFirstName() + " " + this.getLastName();
	}
}
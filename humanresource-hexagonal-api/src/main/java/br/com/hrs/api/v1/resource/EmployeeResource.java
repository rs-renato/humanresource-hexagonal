package br.com.hrs.api.v1.resource;

import java.io.Serializable;
import java.util.Date;

public class EmployeeResource implements Serializable {

	private static final long serialVersionUID = -2414305687799299253L;
	
//	@Null
	private Integer id;
	
//	@NotNull(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class})
	private String firstName;
	
//	@NotNull(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class})
	private String lastName;
	
//	@NotNull(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class})
	private String email;
	
//	@NotNull(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class})
	private String phone;
	
//	@NotNull(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class})
	private Date hireDate;
	
//	@NotNull(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class})
	private Float salary;
	
//	@NotNull(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class})
	private Float commissionPercent;
	
//	@NotNull(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class})
	private Integer managerId;
	
//	@NotEmpty(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class})
	private String jobId;
	
//	@NotNull(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class})
	private Integer departmentId;
	
	public EmployeeResource() {

	}
	
	public EmployeeResource(Integer id, String firstName, String lastName, String email, String phone, Date hireDate,
			Float salary, Float commissionPercent, Integer managerId, String jobId, Integer departmentId) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.hireDate = hireDate;
		this.salary = salary;
		this.commissionPercent = commissionPercent;
		this.managerId = managerId;
		this.jobId = jobId;
		this.departmentId = departmentId;
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

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "EmployeeResource{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", hireDate=" + hireDate +
				", salary=" + salary +
				", commissionPercent=" + commissionPercent +
				", managerId=" + managerId +
				", jobId='" + jobId + '\'' +
				", departmentId=" + departmentId +
				'}';
	}
}
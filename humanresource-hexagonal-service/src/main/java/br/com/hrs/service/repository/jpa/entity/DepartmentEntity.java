package br.com.hrs.service.repository.jpa.entity;

import br.com.hrs.core.model.Department;

import javax.persistence.*;

@Entity(name="Department")
@Table(name = "DEPARTMENTS")
@Cacheable
public class DepartmentEntity extends Department {

	protected DepartmentEntity() {
	}

	public DepartmentEntity(String name, EmployeeEntity manager, LocationEntity location) {
		super(null, name, manager, location);
	}

	public DepartmentEntity(Integer id, String name, EmployeeEntity manager, LocationEntity location) {
		super(id, name, manager, location);
	}

	/**
	 * @return Department identifier.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEPARTMENT_ID")
	public Integer getId() {
		return id;
	}

	/**
	 * @return Department name.
	 */
	@Column(name = "DEPARTMENT_NAME")
	public String getName() {
		return name;
	}

	/**
	 * @return Department manager.
	 */
	@ManyToOne
	@JoinColumn(name = "MANAGER_ID")
	public EmployeeEntity getManager() {
		return (EmployeeEntity)manager;
	}

	/**
	 * @return Department location.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LOCATION_ID", nullable = false)
	public LocationEntity getLocation() {
		return (LocationEntity)location;
	}
}
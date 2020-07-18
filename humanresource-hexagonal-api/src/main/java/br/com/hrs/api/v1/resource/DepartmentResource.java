package br.com.hrs.api.v1.resource;

import br.com.hrs.api.validation.FieldValidationStrategy;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class DepartmentResource implements Serializable {

	private static final long serialVersionUID = -7716241571080910877L;

	@Null(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class, FieldValidationStrategy.Patch.class})
	private Integer id;

	@NotNull(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class})
	@Size(max = 30)
	private String name;

	@NotNull(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class})
	private Integer managerId;

	@NotNull(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class})
	private Integer locationId;

	public DepartmentResource() {

	}

	public DepartmentResource(Integer id, String name, Integer managerId, Integer locationId) {
		this.id = id;
		this.name = name;
		this.managerId = managerId;
		this.locationId = locationId;
	}

	public DepartmentResource(String name, Integer managerId, Integer locationId) {
		this.name = name;
		this.managerId = managerId;
		this.locationId = locationId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	@Override
	public String toString() {
		return "DepartmentResource{" +
				"id=" + id +
				", name='" + name + '\'' +
				", managerId=" + managerId +
				", locationId=" + locationId +
				'}';
	}
}

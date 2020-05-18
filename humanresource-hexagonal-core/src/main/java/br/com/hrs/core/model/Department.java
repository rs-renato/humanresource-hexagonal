package br.com.hrs.core.model;

import java.util.Objects;

public class Department implements EntityKey<Integer>{

	private Integer id;
	private String name;
	private Employee manager;
	private Location location;

	Department() {
	}

	public Department(String name, Employee manager, Location location) {
		this(null, name, manager, location);
	}

	public Department(Integer id, String name, Employee manager, Location location) {
		this.id = id;
		this.name = name;
		this.manager = manager;
		this.location = location;
	}

	@Override
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

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Department that = (Department) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public String toString() {
		return "Department{" +
				"id=" + id +
				", name='" + name + '\'' +
				", manager=" + manager +
				", location=" + location +
				'}';
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public static final class Builder{

		Department department = new Department();

		public Builder id(Integer id){
			this.department.setId(id);
			return this;
		}

		public Builder name(String name){
			this.department.setName(name);
			return this;
		}

		public Builder manager(Employee manager){
			this.department.setManager(manager);
			return this;
		}

		public Builder location(Location location){
			this.department.setLocation(location);
			return this;
		}

		public Department build(){
			return this.department;
		}
	}
}
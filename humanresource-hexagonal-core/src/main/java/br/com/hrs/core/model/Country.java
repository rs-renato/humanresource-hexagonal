package br.com.hrs.core.model;

import java.util.Objects;

public class Country implements EntityKey<String> {

	private String id;
	private String name;
	private Region region;

	Country() {
	}

	public Country(String name, Region region) {
		this(null, name, region);
	}

	public Country(String id, String name, Region region) {
		this.id = id;
		this.name = name;
		this.region = region;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Country country = (Country) o;
		return Objects.equals(id, country.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Country{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", region=" + region +
				'}';
	}

	public static final class Builder{

		Country country = new Country();

		public Builder id(String id){
			this.country.setId(id);
			return this;
		}

		public Builder name(String name){
			this.country.setName(name);
			return this;
		}

		public Builder region(Region region){
			this.country.setRegion(region);
			return this;
		}

		public Country build(){
			return this.country;
		}
	}
}


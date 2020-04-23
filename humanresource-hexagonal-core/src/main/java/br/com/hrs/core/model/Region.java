package br.com.hrs.core.model;

import java.util.List;
import java.util.Objects;

public class Region {

	private Integer id;
	private String name;
	private List<Country> countries;

	Region(){
	}

	public Region(String name, List<Country> countries) {
		this(null, name, countries);
	}

	public Region(Integer id, String name, List<Country> countries) {
		this.id = id;
		this.name = name;
		this.countries = countries;
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

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Region region = (Region) o;
		return Objects.equals(id, region.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public static class Builder {

		private Region region = new Region();

		public Builder id(Integer id) {
			this.region.setId(id);
			return this;
		}

		public Builder name(String name) {
			this.region.setName(name);
			return this;
		}

		public Builder countries(List<Country> countries) {
			this.region.setCountries(countries);
			return this;
		}

		public Region build(){
			return this.region;
		}
	}
}
package br.com.hrs.core.model;

import java.util.Objects;

public class Location implements EntityKey<Integer>{

	private Integer id;
	private String address;
	private String postalCode;
	private String city;
	private String state;
	private Country country;

	public Location(){
	}

	public Location(String address, String postalCode, String city, String state, Country country) {
		this(null, address, postalCode, city, state, country);
	}

	public Location(Integer id, String address, String postalCode, String city, String state, Country country) {
		this.id = id;
		this.address = address;
		this.postalCode = postalCode;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Location location = (Location) o;
		return Objects.equals(id, location.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Location{" +
				"id=" + id +
				", address='" + address + '\'' +
				", postalCode='" + postalCode + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				", country=" + (country != null ? country.getId() : null) +
				'}';
	}

	public static class Builder {

		private Location location = new Location();

		public Builder id(Integer id) {
			this.location.setId(id);
			return this;
		}

		public Builder address(String address) {
			this.location.setAddress(address);
			return this;
		}

		public Builder postalCode(String postalCode) {
			this.location.setPostalCode(postalCode);
			return this;
		}

		public Builder city(String city) {
			this.location.setCity(city);
			return this;
		}

		public Builder state(String state) {
			this.location.setState(state);
			return this;
		}

		public Builder country(Country country) {
			this.location.setCountry(country);
			return this;
		}

		public Location build(){
			return this.location;
		}
	}
}
package br.com.hrs.api.v1.resource;

import br.com.hrs.api.validation.FieldValidationStrategy;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

public class LocationResource implements Serializable {

	private static final long serialVersionUID = 4963875089597531855L;
	
	@Null
	private Integer id;

	@NotNull(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class})
	private String address;

	@NotNull(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class})
	private String postalCode;

	@NotNull(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class})
	private String city;

	@NotNull(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class})
	private String state;

	@NotNull(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class})
	private String countryId;
	
	public LocationResource() {

	}
	
	public LocationResource(String address, String postalCode, String city, String state, String countryId) {
		this(null, address, postalCode, city, state, countryId);
	}

	public LocationResource(Integer id, String address, String postalCode, String city, String state, String countryId) {
		this.id = id;
		this.address = address;
		this.postalCode = postalCode;
		this.city = city;
		this.state = state;
		this.countryId = countryId;
	}

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
	
	public String getCountryId() {
		return countryId;
	}
	
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	@Override
	public String toString() {
		return "LocationResource{" +
				"id=" + id +
				", address='" + address + '\'' +
				", postalCode='" + postalCode + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				", countryId='" + countryId + '\'' +
				'}';
	}
}
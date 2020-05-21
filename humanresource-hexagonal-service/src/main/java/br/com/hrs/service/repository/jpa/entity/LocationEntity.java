package br.com.hrs.service.repository.jpa.entity;

import br.com.hrs.core.model.Location;

import javax.persistence.*;

@Entity(name="Locations")
@Table(name = "LOCATIONS")
@Cacheable
public class LocationEntity extends Location {

	protected LocationEntity(){
	}

	public LocationEntity(String address, String postalCode, String city, String state, CountryEntity country) {
		super(null, address, postalCode, city, state, country);
	}

	public LocationEntity(Integer id, String address, String postalCode, String city, String state, CountryEntity country) {
		super(id, address, postalCode, city, state, country);
	}

	/**
	 * @return Location identifier.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LOCATION_ID")
	public Integer getId() {
		return id;
	}
	
	/**
	 * @return Location address.
	 */
	@Column(name = "STREET_ADDRESS")
	public String getAddress() {
		return address;
	}

	/**
	 * @return Location postal code.
	 */
	@Column(name = "POSTAL_CODE")
	public String getPostalCode() {
		return postalCode;
	}
	
	/**
	 * @return Location city.
	 */
	@Column(name = "CITY")
	public String getCity() {
		return city;
	}
	
	/**
	 * @return Location state.
	 */
	@Column(name = "STATE_PROVINCE")
	public String getState() {
		return state;
	}

	/**
	 * @return Location country.
	 */
	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID", nullable = false)
	public CountryEntity getCountry() {
		return (CountryEntity)country;
	}

	@Transient
	public String getFullAddress() {
		return
			this.getAddress() +
			 ", " + this.getPostalCode() +
			 ", " + this.getCity() +
			(this.getState() != null && !this.getState().equals("") ?  (" - " + this.getState()) : "" ) +
			((this.getCountry() != null && this.getCountry().getName() != null) ? (" - " + this.getCountry().getName()) : "");
	}
}

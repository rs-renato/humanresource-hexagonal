package br.com.hrs.service.repository.jpa.entity;

import br.com.hrs.core.model.Country;

//@Entity(name="Country")
//@Table(name = "COUNTRIES")
//@Cacheable
public class CountryEntity extends Country {

	public CountryEntity() {
	}

	public CountryEntity(String name, RegionEntity region) {
		super(name, region);
	}

	public CountryEntity(String id, String name, RegionEntity region) {
		super(id, name, region);
	}

	/**
	 * @return Country identifier.
	 */
//	@Id
//	@Column(name = "COUNTRY_ID")
	public String getId() {
		return id;
	}

	/**
	 * @return Country name.
	 */
//	@Column(name = "COUNTRY_NAME")
	public String getName() {
		return name;
	}

	/**
	 * @return Country region.
	 */
//	@ManyToOne
//	@JoinColumn(name = "REGION_ID", nullable = false)
	public RegionEntity getRegion() {
		return (RegionEntity)region;
	}
}
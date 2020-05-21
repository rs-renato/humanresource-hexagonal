package br.com.hrs.service.repository.jpa.entity;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Region;

import javax.persistence.*;
import java.util.List;

@Entity(name="Region")
@Table(name = "REGIONS")
@Cacheable
public class RegionEntity extends Region {

	protected RegionEntity(){
	}

	public RegionEntity(String name) {
		super(name, null);
	}

	public RegionEntity(String name, List<? extends Country> countries) {
		super(null, name, null);
	}

	public RegionEntity(Integer id, String name, List<? extends Country> countries) {
		super(id, name, null);
	}

	/**
	 * @return Region identifier.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REGION_ID")
	public Integer getId() {
		return id;
	}

	/**
	 * @return Region name.
	 */
	@Column(name = "REGION_NAME")
	public String getName() {
		return name;
	}

	/**
	 * @return Countries located in this region.
	 */
	@OneToMany(mappedBy="region", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Country> getCountries() {
		return null;
	}
}
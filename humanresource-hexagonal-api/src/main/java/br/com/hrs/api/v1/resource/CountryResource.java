package br.com.hrs.api.v1.resource;

import br.com.hrs.api.validation.FieldValidationStrategy;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class CountryResource implements Serializable {

	private static final long serialVersionUID = 7728812557431552908L;

	@Null(groups= {FieldValidationStrategy.Update.class, FieldValidationStrategy.Patch.class})
	@NotNull(groups=FieldValidationStrategy.Create.class)
	@Size(min=2, max=2, groups=FieldValidationStrategy.Create.class)
	private String id;
	
	@NotNull(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class})
	@Size(max = 40)
	private String name;

	@NotNull(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class})
	private Integer regionId;
	
	public CountryResource() {

	}
	
	public CountryResource(String id, Integer regionId) {
		this(id, null, regionId);
	}
	
	public CountryResource(String id, String name, Integer regionId) {
		this.id = id;
		this.name = name;
		this.regionId = regionId;
	}

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

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	@Override
	public String toString() {
		return "CountryResource{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", regionId=" + regionId +
				'}';
	}
}

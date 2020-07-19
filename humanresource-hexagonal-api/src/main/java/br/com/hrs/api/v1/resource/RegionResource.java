package br.com.hrs.api.v1.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

public class RegionResource implements Serializable{
	
	private static final long serialVersionUID = 5332696213329433734L;

	@Null
	private Integer id;

	@NotNull
	private String name;
	
	public RegionResource() {

	}

	public RegionResource(Integer id, String name) {
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "RegionResource{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}

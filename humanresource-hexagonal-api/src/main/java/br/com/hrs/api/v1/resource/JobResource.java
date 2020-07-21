package br.com.hrs.api.v1.resource;

import br.com.hrs.api.validation.FieldValidationStrategy;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class JobResource implements Serializable {

	private static final long serialVersionUID = -1572938058160039682L;
	
	@Null(groups= {FieldValidationStrategy.Update.class, FieldValidationStrategy.Patch.class})
	@NotNull(groups=FieldValidationStrategy.Create.class)
	@Size(min=1, max=10, groups=FieldValidationStrategy.Create.class)
	private String id;
	
	@NotNull
	@Size(min=3, max=35)
	private String title;

	@NotNull(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class})
	private Integer minSalary;

	@NotNull(groups= {FieldValidationStrategy.Create.class, FieldValidationStrategy.Update.class})
	private Integer maxSalary;
	
	public JobResource() {

	}

	public JobResource(String title, Integer minSalary, Integer maxSalary) {
		this(null, title, minSalary, maxSalary);
	}

	public JobResource(String id, String title, Integer minSalary, Integer maxSalary) {
		this.id = id;
		this.title = title;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Integer getMinSalary() {
		return minSalary;
	}
	
	public void setMinSalary(Integer minSalary) {
		this.minSalary = minSalary;
	}
	
	public Integer getMaxSalary() {
		return maxSalary;
	}
	
	public void setMaxSalary(Integer maxSalary) {
		this.maxSalary = maxSalary;
	}

	@Override
	public String toString() {
		return "JobResource{" +
				"id='" + id + '\'' +
				", title='" + title + '\'' +
				", minSalary=" + minSalary +
				", maxSalary=" + maxSalary +
				'}';
	}
}
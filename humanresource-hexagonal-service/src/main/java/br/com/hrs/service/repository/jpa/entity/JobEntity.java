package br.com.hrs.service.repository.jpa.entity;

import br.com.hrs.core.model.Job;

import javax.persistence.*;

@Entity(name="Job")
@Table(name = "JOBS")
@Cacheable
public class JobEntity extends Job {

	protected JobEntity(){
	}

	public JobEntity(String title, Float minSalary, Float maxSalary) {
		super(null, title, minSalary, maxSalary);
	}

	public JobEntity(String id, String title, Float minSalary, Float maxSalary) {
		super(id, title, minSalary, maxSalary);
	}

	/**
	 * @return Job identifier.
	 */
	@Id
	@Column(name = "JOB_ID")
	public String getId() {
		return id;
	}

	/**
	 * @return Job title.
	 */
	@Column(name = "JOB_TITLE")
	public String getTitle() {
		return title;
	}

	/**
	 * @return Job minimum salary.
	 */
	@Column(name = "MIN_SALARY")
	public Float getMinSalary() {
		return minSalary;
	}
	
	/**
	 * @return Job maximum salary.
	 */
	@Column(name = "MAX_SALARY")
	public Float getMaxSalary() {
		return maxSalary;
	}
}
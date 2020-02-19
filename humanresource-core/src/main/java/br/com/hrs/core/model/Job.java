package br.com.hrs.core.model;

import java.util.Objects;

public class Job {

	private String id;
	private String title;
	private Float minSalary;
	private Float maxSalary;

	public Job(String id, String title, Float minSalary, Float maxSalary) {
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

	public Float getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(Float minSalary) {
		this.minSalary = minSalary;
	}

	public Float getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(Float maxSalary) {
		this.maxSalary = maxSalary;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Job job = (Job) o;
		return Objects.equals(id, job.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Job{" +
				"id='" + id + '\'' +
				", title='" + title + '\'' +
				", minSalary=" + minSalary +
				", maxSalary=" + maxSalary +
				'}';
	}
}
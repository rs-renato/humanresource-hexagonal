package br.com.hrs.core.validator.impl.employee.shared;

import br.com.hrs.core.exception.HrsBusinessException;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.validator.SaveValidator;
import br.com.hrs.core.validator.UpdateValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class MaxSalaryAllowedEmployeeValidator implements SaveValidator<Employee>, UpdateValidator<Employee> {

	private Repository<Job, String> jobRepository;

	@Inject
	public MaxSalaryAllowedEmployeeValidator(Repository<Job, String> jobRepository) {
		this.jobRepository = jobRepository;
	}

	@Override
	public void validate(Employee employee) {
		Job job = this.jobRepository.find(employee.getJob().getId());
		logger.debug("Validating if employee's salary is allowed compared with max job's salary");
		Float maxSalary = job.getMaxSalary();
		if(employee.getSalary() > maxSalary) {
			throw new HrsBusinessException(String.format("The employee salary is invalid according to the job's max salary (%s)", maxSalary));
		}
	}
}

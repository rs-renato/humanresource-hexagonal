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
public class MinSalaryAllowedEmployeeValidator implements SaveValidator<Employee>, UpdateValidator<Employee> {

	private Repository<Job, String> jobRepository;

	@Inject
	public MinSalaryAllowedEmployeeValidator(Repository<Job, String> jobRepository) {
		this.jobRepository = jobRepository;
	}

	@Override
	public void validate(Employee employee) {
		Job job = this.jobRepository.find(employee.getJob().getId());
		logger.debug("Validating if employee's salary is allowed compared with min job's salary");
		Float minSalary = job.getMinSalary();
		if(employee.getSalary() < minSalary) {
			throw new HrsBusinessException(String.format("The employee salary is invalid according to the job's min salary (%s)", minSalary));
		}
	}
}

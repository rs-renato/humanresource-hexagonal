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
public class ExistentJobEmployeeValidator implements SaveValidator<Employee>, UpdateValidator<Employee> {

	private Repository<Job, String> jobRepository;

	@Inject
	public ExistentJobEmployeeValidator(Repository<Job, String> jobRepository) {
		this.jobRepository = jobRepository;
	}

	@Override
	public void validate(Employee employee) {
		logger.debug("Validating if employee's job is existent");
		if (!this.jobRepository.exists(employee.getJob().getId())) {
			throw new HrsBusinessException("Job does not exist");
		}
	}
}
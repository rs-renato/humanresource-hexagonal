package br.com.hrs.core.validator.employee;

import br.com.hrs.core.exception.HrsNotFoundException;
import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.validator.SaveValidator;
import br.com.hrs.core.validator.UpdateValidator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

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

		if (Objects.isNull(employee)) {
			Error.of("Employee").when(FIELD.MANDATORY).trows();
		}

		if (!this.jobRepository.existsById(employee.getJob().getId())) {
			throw new HrsNotFoundException("Job does not exist");
		}
	}
}
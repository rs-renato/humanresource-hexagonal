package br.com.hrs.core.validator.employee;

import br.com.hrs.core.exception.HrsBusinessException;
import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.JobRepository;
import br.com.hrs.core.validator.SaveValidator;
import br.com.hrs.core.validator.UpdateValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;
import java.util.Optional;

@Named
public class MinSalaryAllowedEmployeeValidator implements SaveValidator<Employee>, UpdateValidator<Employee> {

	private static final Logger logger = LogManager.getLogger(MinSalaryAllowedEmployeeValidator.class);

	private JobRepository jobRepository;

	@Inject
	public MinSalaryAllowedEmployeeValidator(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	@Override
	public void validate(Employee employee) {
		logger.debug("Validating if employee's salary is allowed compared with min job's salary");

		if (Objects.isNull(employee)) {
			Error.of("Employee").when(FIELD.MANDATORY).trows();
		}

		Optional<Job> job = this.jobRepository.findById(employee.getJob().getId());
		Float minSalary = job.get().getMinSalary();
		if(employee.getSalary() < minSalary) {
			throw new HrsBusinessException(String.format("The employee salary is invalid according to the job's min salary (%s)", minSalary));
		}
	}
}

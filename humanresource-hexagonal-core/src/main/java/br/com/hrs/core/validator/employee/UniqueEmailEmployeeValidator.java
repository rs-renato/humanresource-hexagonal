package br.com.hrs.core.validator.employee;

import br.com.hrs.core.exception.HrsNotFoundException;
import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.repository.EmployeeRepository;
import br.com.hrs.core.validator.SaveValidator;
import br.com.hrs.core.validator.UpdateValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class UniqueEmailEmployeeValidator implements SaveValidator<Employee>, UpdateValidator<Employee> {

	private static final Logger logger = LogManager.getLogger(UniqueEmailEmployeeValidator.class);

	private EmployeeRepository employeeRepository;

	@Inject
	public UniqueEmailEmployeeValidator(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public void validate(Employee employee) {
		logger.debug("Validating if employee's email is unique");

		if (Objects.isNull(employee)) {
			Error.of("Employee").when(FIELD.MANDATORY).trows();
		}

		boolean exists = this.employeeRepository.existsByEmail(employee.getEmail());

		if(employee.getId() == null && exists
			|| (exists && !this.employeeRepository.findById(employee.getId()).get().getEmail().equals(employee.getEmail()))){
			throw new HrsNotFoundException("Email should be unique");
		}
	}
}
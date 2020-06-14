package br.com.hrs.core.validator.employee;

import br.com.hrs.core.exception.HrsNotFoundException;
import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.repository.EmployeeRepository;
import br.com.hrs.core.validator.SaveValidator;
import br.com.hrs.core.validator.UpdateValidator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class ExistentManagerEmployeeValidator implements SaveValidator<Employee>, UpdateValidator<Employee> {

	private EmployeeRepository employeeRepository;

	@Inject
	public ExistentManagerEmployeeValidator(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public void validate(Employee manager) {
		logger.debug("Validating if manager is existent");

		if (Objects.isNull(manager)) {
			Error.of("Employee").when(FIELD.MANDATORY).trows();
		}

		if(!this.employeeRepository.existsById(manager.getId())){
			throw new HrsNotFoundException("Manager does not exist");
		}
	}
}
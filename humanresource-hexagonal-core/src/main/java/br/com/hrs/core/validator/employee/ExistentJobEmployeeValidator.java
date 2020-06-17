package br.com.hrs.core.validator.employee;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.validator.SaveValidator;
import br.com.hrs.core.validator.UpdateValidator;
import br.com.hrs.core.validator.job.ExistentJobValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class ExistentJobEmployeeValidator implements SaveValidator<Employee>, UpdateValidator<Employee> {

	private static final Logger logger = LogManager.getLogger(ExistentJobEmployeeValidator.class);

	private ExistentJobValidator existentJobValidator;

	@Inject
	public ExistentJobEmployeeValidator(ExistentJobValidator existentJobValidator) {
		this.existentJobValidator = existentJobValidator;
	}

	@Override
	public void validate(Employee employee) {
		logger.debug("Validating if employee's job is existent");

		if (Objects.isNull(employee)) {
			Error.of("Employee").when(FIELD.MANDATORY).trows();
		}

		this.existentJobValidator.validate(employee.getJob());
	}
}
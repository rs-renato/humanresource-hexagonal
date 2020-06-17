package br.com.hrs.core.validator.employee;

import br.com.hrs.core.exception.HrsBusinessException;
import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.validator.SaveValidator;
import br.com.hrs.core.validator.UpdateValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Named;
import java.util.Objects;

@Named
public class MaxCommissionPercentAllowedEmployeeValidator implements SaveValidator<Employee>, UpdateValidator<Employee> {

	private static final Logger logger = LogManager.getLogger(MaxCommissionPercentAllowedEmployeeValidator.class);

	public static final Float MAX_COMMISSION_PERCENT = 0.99f;

	@Override
	public void validate(Employee employee) {
		logger.debug("Validating if employee's commission percent is greater than max commission percent");

		if (Objects.isNull(employee)) {
			Error.of("Employee").when(FIELD.MANDATORY).trows();
		}

		if(employee.getCommissionPercent() > MAX_COMMISSION_PERCENT) {
			throw new HrsBusinessException(String.format("The employee commission percent is invalid according to the max commission percent (%s)", MAX_COMMISSION_PERCENT));
		}
	}
}

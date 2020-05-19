package br.com.hrs.core.validator.impl.employee.shared;

import br.com.hrs.core.exception.HrsBusinessException;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.validator.SaveValidator;
import br.com.hrs.core.validator.UpdateValidator;

import javax.inject.Named;

@Named
public class MaxCommissionPercentAllowedEmployeeValidator implements SaveValidator<Employee>, UpdateValidator<Employee> {

	public static final Float MAX_COMMISSION_PERCENT = 0.99f;

	@Override
	public void validate(Employee employee) {
		logger.debug("Validating if employee's commission percent is greater than max commission percent");
		if(employee.getCommissionPercent() > MAX_COMMISSION_PERCENT) {
			throw new HrsBusinessException(String.format("The employee commission percent is invalid according to the max commission percent (%s)", MAX_COMMISSION_PERCENT));
		}
	}
}

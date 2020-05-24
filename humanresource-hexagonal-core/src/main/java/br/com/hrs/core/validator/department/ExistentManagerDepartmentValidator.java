package br.com.hrs.core.validator.department;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.validator.SaveValidator;
import br.com.hrs.core.validator.UpdateValidator;
import br.com.hrs.core.validator.employee.ExistentManagerEmployeeValidator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class ExistentManagerDepartmentValidator implements SaveValidator<Department>, UpdateValidator<Department> {

	private ExistentManagerEmployeeValidator existentManagerValidator;

	@Inject
	public ExistentManagerDepartmentValidator(ExistentManagerEmployeeValidator existentManagerValidator) {
		this.existentManagerValidator = existentManagerValidator;
	}

	@Override
	public void validate(Department department) {

		logger.debug("Validating if department's manager is existent");

		if (Objects.isNull(department)) {
			Error.of("Department").when(FIELD.MANDATORY).trows();
		}

		this.existentManagerValidator.validate(department.getManager());
	}
}
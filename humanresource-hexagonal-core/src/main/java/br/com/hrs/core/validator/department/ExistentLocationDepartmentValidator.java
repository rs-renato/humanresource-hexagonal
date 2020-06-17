package br.com.hrs.core.validator.department;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.validator.SaveValidator;
import br.com.hrs.core.validator.UpdateValidator;
import br.com.hrs.core.validator.location.ExistentLocationValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class ExistentLocationDepartmentValidator implements SaveValidator<Department>, UpdateValidator<Department> {

	private static final Logger logger = LogManager.getLogger(ExistentLocationDepartmentValidator.class);

	private ExistentLocationValidator existentLocationValidator;

	@Inject
	public ExistentLocationDepartmentValidator(ExistentLocationValidator existentLocationValidator) {
		this.existentLocationValidator = existentLocationValidator;
	}

	@Override
	public void validate(Department department) {

		logger.debug("Validating if department's manager is existent");

		if (Objects.isNull(department)) {
			Error.of("Department").when(FIELD.MANDATORY).trows();
		}

		this.existentLocationValidator.validate(department.getLocation());
	}
}
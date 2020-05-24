package br.com.hrs.core.validator.department;

import br.com.hrs.core.exception.HrsNotFoundException;
import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.validator.SaveValidator;
import br.com.hrs.core.validator.UpdateValidator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class ExistentLocationDepartmentValidator implements SaveValidator<Department>, UpdateValidator<Department> {

	private Repository<Location, Integer> departmentRepository;

	@Inject
	public ExistentLocationDepartmentValidator(Repository<Location, Integer> departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public void validate(Department department) {

		logger.debug("Validating if department's manager is existent");

		if (Objects.isNull(department)) {
			Error.of("Department").when(FIELD.MANDATORY).trows();
		}

		Location location = department.getLocation();
		if (location == null || !departmentRepository.existsById(location.getId())) {
			throw new HrsNotFoundException("Location does not exist");
		}
	}
}
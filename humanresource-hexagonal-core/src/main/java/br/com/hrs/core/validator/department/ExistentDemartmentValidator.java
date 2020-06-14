package br.com.hrs.core.validator.department;

import br.com.hrs.core.exception.HrsNotFoundException;
import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.repository.DepartmentRepository;
import br.com.hrs.core.validator.DeleteValidator;
import br.com.hrs.core.validator.UpdateValidator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class ExistentDemartmentValidator implements UpdateValidator<Department>, DeleteValidator<Department> {

    private DepartmentRepository departmentRepository;

    @Inject
    public ExistentDemartmentValidator(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void validate(Department department) {

        logger.debug("Validating if department is existent");

        if (Objects.isNull(department)) {
            Error.of("Department").when(FIELD.MANDATORY).trows();
        }

        if (!departmentRepository.existsById(department.getId())) {
            throw new HrsNotFoundException("Department does not exist");
        }
    }
}

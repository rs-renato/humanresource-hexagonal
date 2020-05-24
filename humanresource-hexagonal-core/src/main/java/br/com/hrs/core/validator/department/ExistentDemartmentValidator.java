package br.com.hrs.core.validator.department;

import br.com.hrs.core.exception.HrsNotFoundException;
import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.validator.Validator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class ExistentDemartmentValidator implements Validator<Department> {

    private Repository<Department, Integer> departmentRepository;

    @Inject
    public ExistentDemartmentValidator(Repository<Department, Integer> departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void validate(Department department) {

        logger.debug("Validating if department is existent");

        if (Objects.isNull(department)) {
            Error.of("Department").when(FIELD.MANDATORY).trows();
        }

        if (!departmentRepository.exists(department.getId())) {
            throw new HrsNotFoundException("Department does not exist");
        }
    }
}

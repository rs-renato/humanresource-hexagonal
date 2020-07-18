package br.com.hrs.core.validator.department;

import br.com.hrs.core.exception.HrsBusinessException;
import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.repository.DepartmentRepository;
import br.com.hrs.core.validator.SaveValidator;
import br.com.hrs.core.validator.UpdateValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class ExistentDemartmentNameValidator implements SaveValidator<Department>, UpdateValidator<Department> {

    private static final Logger logger = LogManager.getLogger(ExistentDemartmentNameValidator.class);

    private DepartmentRepository departmentRepository;

    @Inject
    public ExistentDemartmentNameValidator(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void validate(Department department) {

        logger.debug("Validating if department name is existent");

        if (Objects.isNull(department)) {
            Error.of("Department").when(FIELD.MANDATORY).trows();
        }

        if(departmentRepository.existsByName(department.getName())
            && !departmentRepository.findById(department.getId()).get().getName().equals(department.getName())) {
            throw new HrsBusinessException("Department name exists");
        }
    }
}

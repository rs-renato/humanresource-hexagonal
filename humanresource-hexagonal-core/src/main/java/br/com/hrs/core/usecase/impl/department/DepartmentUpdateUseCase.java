package br.com.hrs.core.usecase.impl.department;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.UpdateUseCase;
import br.com.hrs.core.validator.UpdateValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DepartmentUpdateUseCase extends UpdateUseCase<Department, Integer> {

    @Inject
    public DepartmentUpdateUseCase(Repository<Department, Integer> repository, UpdateValidator<Department>...validators) {
        super(repository, validators);
    }
}
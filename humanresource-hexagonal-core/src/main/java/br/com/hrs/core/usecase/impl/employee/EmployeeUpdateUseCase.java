package br.com.hrs.core.usecase.impl.employee;

import br.com.hrs.core.model.Employee;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.UpdateUseCase;
import br.com.hrs.core.validator.UpdateValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EmployeeUpdateUseCase extends UpdateUseCase<Employee, Integer> {

    @Inject
    public EmployeeUpdateUseCase(Repository<Employee, Integer> repository, UpdateValidator<Employee>...validators) {
        super(repository, validators);
    }
}
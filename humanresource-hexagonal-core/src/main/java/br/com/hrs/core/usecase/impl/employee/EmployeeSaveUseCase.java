package br.com.hrs.core.usecase.impl.employee;

import br.com.hrs.core.model.Employee;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.SaveUseCase;
import br.com.hrs.core.validator.SaveValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EmployeeSaveUseCase extends SaveUseCase<Employee, Integer> {

    @Inject
    public EmployeeSaveUseCase(Repository<Employee, Integer> repository, SaveValidator<Employee>...validators) {
        super(repository, validators);
    }
}
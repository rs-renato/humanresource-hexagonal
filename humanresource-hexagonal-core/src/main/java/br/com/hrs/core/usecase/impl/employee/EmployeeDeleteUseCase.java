package br.com.hrs.core.usecase.impl.employee;

import br.com.hrs.core.model.Employee;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.DeleteUseCase;
import br.com.hrs.core.validator.DeleteValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EmployeeDeleteUseCase extends DeleteUseCase<Employee, Integer> {

    @Inject
    public EmployeeDeleteUseCase(Repository<Employee, Integer> repository, DeleteValidator<Employee>...validators) {
        super(repository, validators);
    }
}
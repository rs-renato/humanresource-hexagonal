package br.com.hrs.core.usecase.impl.employee;

import br.com.hrs.core.model.Employee;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.FindUseCase;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EmployeeFindUseCase extends FindUseCase<Employee, Integer> {

    @Inject
    public EmployeeFindUseCase(Repository<Employee, Integer> repository) {
        super(repository);
    }
}

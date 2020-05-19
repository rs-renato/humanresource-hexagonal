package br.com.hrs.core.usecase.impl.employee;

import br.com.hrs.core.model.Employee;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.CrudUseCase;
import br.com.hrs.core.validator.Validator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EmployeeCrudUseCase extends CrudUseCase<Employee, Integer> {

	@Inject
	public EmployeeCrudUseCase(Repository<Employee, Integer> repository, Validator<Employee>... validators) {
		super(repository, validators);
	}
}
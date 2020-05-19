package br.com.hrs.core.usecase.impl.department;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.CrudUseCase;
import br.com.hrs.core.validator.Validator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DepartmentCrudUseCase extends CrudUseCase<Department, Integer> {

	@Inject
	public DepartmentCrudUseCase(Repository<Department, Integer> repository, Validator<Department>... validators) {
		super(repository, validators);
	}
}
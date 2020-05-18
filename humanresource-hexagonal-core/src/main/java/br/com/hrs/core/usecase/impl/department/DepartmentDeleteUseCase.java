package br.com.hrs.core.usecase.impl.department;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.DeleteUseCase;
import br.com.hrs.core.validator.DeleteValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DepartmentDeleteUseCase extends DeleteUseCase<Department, Integer> {

	@Inject
	public DepartmentDeleteUseCase(Repository<Department, Integer> repository, DeleteValidator<Department>... validators) {
		super(repository, validators);
	}
}
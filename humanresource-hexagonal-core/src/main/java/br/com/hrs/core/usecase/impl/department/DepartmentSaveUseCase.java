package br.com.hrs.core.usecase.impl.department;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.SaveUseCase;
import br.com.hrs.core.validator.SaveValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DepartmentSaveUseCase extends SaveUseCase<Department, Integer> {
	
	@Inject
	public DepartmentSaveUseCase(Repository<Department, Integer> repository, SaveValidator<Department>... validators) {
		super(repository, validators);
	}
}

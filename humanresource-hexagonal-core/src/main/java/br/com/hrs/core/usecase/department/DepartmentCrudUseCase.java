package br.com.hrs.core.usecase.department;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.repository.DepartmentRepository;
import br.com.hrs.core.usecase.CrudAbstractUseCaseImpl;
import br.com.hrs.core.validator.Validator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Named
class DepartmentCrudUseCase extends CrudAbstractUseCaseImpl<Department, Integer> implements DepartmentUseCase {

	private DepartmentRepository departmentRepository;
	private List<Validator<Department>> validators;

	@Inject
	public DepartmentCrudUseCase(DepartmentRepository departmentRepository, Validator<Department>... validators) {
		this.departmentRepository = departmentRepository;
		this.validators = validators != null ? new LinkedList<>(Arrays.asList(validators)) : new ArrayList<>();
	}

	@Override
	protected DepartmentRepository getRepository() {
		return this.departmentRepository;
	}

	@Override
	protected List<Validator<Department>> getValidators() {
		return validators;
	}
}
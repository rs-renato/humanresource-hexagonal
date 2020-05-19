package br.com.hrs.core.validator.impl.employee.shared;

import br.com.hrs.core.exception.HrsBusinessException;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.validator.SaveValidator;
import br.com.hrs.core.validator.UpdateValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ExistentDepartmentEmployeeValidator implements SaveValidator<Employee>, UpdateValidator<Employee> {

	private Repository<Department, Integer> departmentRepository;

	@Inject
	public ExistentDepartmentEmployeeValidator(Repository<Department, Integer> departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public void validate(Employee employee) {
		logger.debug("Validating if employee's department is existent");
		if (!this.departmentRepository.exists(employee.getDepartment().getId())) {
			throw new HrsBusinessException("Department does not exist");
		}
	}
}
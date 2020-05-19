package br.com.hrs.core.validator.impl.employee.shared;

import br.com.hrs.core.exception.HrsBusinessException;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.validator.SaveValidator;
import br.com.hrs.core.validator.UpdateValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ExistentManagerEmployeeValidator implements SaveValidator<Employee>, UpdateValidator<Employee> {

	private Repository<Employee, Integer> employeeRepository;

	@Inject
	public ExistentManagerEmployeeValidator(Repository<Employee, Integer> employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public void validate(Employee employee) {
		logger.debug("Validating if employee's manager is existent");
		if(!this.employeeRepository.exists(employee.getManager().getId())){
			throw new HrsBusinessException("Manager does not exist");
		}
	}
}
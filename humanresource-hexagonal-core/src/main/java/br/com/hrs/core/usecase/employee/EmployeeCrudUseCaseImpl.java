package br.com.hrs.core.usecase.employee;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.CrudAbstractUseCaseImpl;
import br.com.hrs.core.validator.Validator;
import br.com.hrs.core.validator.employee.PromotionValidator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Named
class EmployeeCrudUseCaseImpl extends CrudAbstractUseCaseImpl<Employee, Integer> implements EmployeeUseCase {

	private Repository<Employee,Integer> employeeRepository;
	private Repository<Job,String> jobRepository;
	private Repository<Department,Integer> departmentRepository;
	private List<Validator<Employee>> validators;

	@Inject
	public EmployeeCrudUseCaseImpl(Repository<Employee,Integer> employeeRepository,
								   Repository<Job,String> jobRepository,
								   Repository<Department,Integer> departmentRepository,
								   Validator<Employee>... validators) {

		this.employeeRepository = employeeRepository;
		this.jobRepository = jobRepository;
		this.departmentRepository = departmentRepository;

		this.validators = validators != null ? new LinkedList<>(Arrays.asList(validators)) : new ArrayList<>();
	}

	@Override
	protected Repository<Employee,Integer> getRepository() {
		return this.employeeRepository;
	}

	@Override
	protected List<Validator<Employee>> getValidators() {
		return validators;
	}

	@Override
	public void promote(Integer employeeId, String jobId, Integer departmentId) {

		logger.info("Promoting employee '{}', to job '{} from department '{}", employeeId, jobId, departmentId);

		Employee employee = this.employeeRepository.find(employeeId);
		Job job = this.jobRepository.find(jobId);
		Department department = this.departmentRepository.find(departmentId);

		this.validators.stream()
				.filter(validator -> validator instanceof PromotionValidator)
				.map(validator -> (PromotionValidator)validator)
				.forEach(validator -> validator.validate(employee, job, department));

		employee.setSalary(job.getMinSalary());
		employee.setDepartment(department);
		employee.setJob(job);

		employeeRepository.update(employee);

		logger.info("Employee '{}' promoted!", employee);
	}
}
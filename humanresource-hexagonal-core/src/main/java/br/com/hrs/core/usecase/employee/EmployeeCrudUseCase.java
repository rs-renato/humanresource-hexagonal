package br.com.hrs.core.usecase.employee;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.DepartmentRepository;
import br.com.hrs.core.repository.EmployeeRepository;
import br.com.hrs.core.repository.JobRepository;
import br.com.hrs.core.usecase.CrudAbstractUseCaseImpl;
import br.com.hrs.core.validator.Validator;
import br.com.hrs.core.validator.employee.PromotionValidator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

@Named
class EmployeeCrudUseCase extends CrudAbstractUseCaseImpl<Employee, Integer> implements EmployeeUseCase {

	private EmployeeRepository employeeRepository;
	private JobRepository jobRepository;
	private DepartmentRepository departmentRepository;
	private List<Validator<Employee>> validators;

	@Inject
	public EmployeeCrudUseCase(EmployeeRepository employeeRepository,
							   JobRepository jobRepository,
							   DepartmentRepository departmentRepository,
							   Validator<Employee>... validators) {

		this.employeeRepository = employeeRepository;
		this.jobRepository = jobRepository;
		this.departmentRepository = departmentRepository;

		this.validators = validators != null ? new LinkedList<>(Arrays.asList(validators)) : new ArrayList<>();
	}

	@Override
	protected EmployeeRepository getRepository() {
		return this.employeeRepository;
	}

	@Override
	protected List<Validator<Employee>> getValidators() {
		return validators;
	}

	@Override
	public void promote(Integer employeeId, String jobId, Integer departmentId) {

		logger.info("Promoting employee '{}', to job '{} from department '{}", employeeId, jobId, departmentId);

		Optional<Employee> employeeOpt = this.employeeRepository.findById(employeeId);
		Optional<Job> job = this.jobRepository.findById(jobId);
		Optional<Department> department = this.departmentRepository.findById(departmentId);

		this.validators.stream()
				.filter(validator -> validator instanceof PromotionValidator)
				.map(validator -> (PromotionValidator)validator)
				.forEach(validator -> validator.validate(employeeOpt, job, department));


		employeeOpt.get().setSalary(job.get().getMinSalary());
		employeeOpt.get().setDepartment(department.get());
		employeeOpt.get().setJob(job.get());

		employeeRepository.update(employeeOpt.get());

		logger.info("Employee '{}' promoted!", employeeOpt);
	}
}
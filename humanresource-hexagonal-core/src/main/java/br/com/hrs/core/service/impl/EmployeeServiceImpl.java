package br.com.hrs.core.service.impl;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class EmployeeServiceImpl implements EmployeeService {

    private Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

    private Repository<Employee, Integer> employeeRepository;
    private Repository<Job, String> jobRepository;
    private Repository<Department, Integer> departmentRepository;

    @Inject
    public EmployeeServiceImpl(Repository<Employee, Integer> employeeRepository, Repository<Job, String> jobRepository, Repository<Department, Integer> departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Employee get(Integer employee) {
        return employeeRepository.find(employee);
    }

    @Override
    public void promote(Integer employeeId, String jobId, Integer departmentId) {

        logger.info("Promoting employee '{}', to job '{} from department '{}", employeeId, jobId, departmentId);

        if (Objects.isNull(employeeId)) {
            Error.of("Employee ID").when(FIELD.MANDATORY).trows();
        }

        if (Objects.isNull(jobId)) {
            Error.of("Job ID").when(FIELD.MANDATORY).trows();
        }

        if (Objects.isNull(departmentId)) {
            Error.of("Department ID").when(FIELD.MANDATORY).trows();
        }

        Employee employee = employeeRepository.find(employeeId);

        if (Objects.isNull(employee)) {
            Error.of("Employee").when(FIELD.NOT_FOUND).trows();
        }

        Job job = jobRepository.find(jobId);

        if (Objects.isNull(job)) {
            Error.of("Job").when(FIELD.NOT_FOUND).trows();
        }

        Department department = departmentRepository.find(departmentId);

        if (Objects.isNull(department)) {
            Error.of("Department").when(FIELD.NOT_FOUND).trows();
        }

        employee.setSalary(job.getMinSalary());
        employee.setDepartment(department);
        employee.setJob(job);

        employeeRepository.update(employee);

        logger.info("Employee '{}' promoted!", employeeId);
    }
}

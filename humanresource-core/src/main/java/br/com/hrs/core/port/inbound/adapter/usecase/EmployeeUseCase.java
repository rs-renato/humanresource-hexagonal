package br.com.hrs.core.port.inbound.adapter.usecase;

import br.com.hrs.core.business.service.EmployeePromotionService;
import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.port.inbound.EmployeePromotion;
import br.com.hrs.core.port.outbound.DepartmentRepository;
import br.com.hrs.core.port.outbound.EmployeeRepository;
import br.com.hrs.core.port.outbound.JobRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Named;
import java.util.Objects;

@Named
public class EmployeeUseCase implements EmployeePromotion {

    private Logger logger = LogManager.getLogger(EmployeeUseCase.class);

    private EmployeePromotionService service;
    private EmployeeRepository employeeRepository;
    private JobRepository jobRepository;
    private DepartmentRepository departmentRepository;

    public EmployeeUseCase(EmployeePromotionService service, EmployeeRepository employeeRepository, JobRepository jobRepository, DepartmentRepository departmentRepository) {
        this.service = service;
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Employee get(Integer employee) {
        return employeeRepository.get(employee);
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

        Employee employee = employeeRepository.get(employeeId);

        if (Objects.isNull(employee)) {
            Error.of("Employee").when(FIELD.NOT_FOUND).trows();
        }

        Job job = jobRepository.get(jobId);

        if (Objects.isNull(job)) {
            Error.of("Job").when(FIELD.NOT_FOUND).trows();
        }

        Department department = departmentRepository.get(departmentId);

        if (Objects.isNull(department)) {
            Error.of("Department").when(FIELD.NOT_FOUND).trows();
        }

        service.promote(employee, job, department);
        employeeRepository.save(employee);

        logger.info("Employee '{}' promoted!", employeeId);
    }
}

package br.com.hrs.core.usecase.impl.employee;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.usecase.CrudUseCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class PromoteEmployeeUseCase {

    private Logger logger = LogManager.getLogger(PromoteEmployeeUseCase.class);

    private CrudUseCase<Employee, Integer> employeeCrudUseCase;
    private CrudUseCase<Job, String> jobCrudUseCase;
    private CrudUseCase<Department, Integer> departmentCrudUseCase;

    @Inject
    public PromoteEmployeeUseCase(CrudUseCase<Employee, Integer> employeeCrudUseCase, CrudUseCase<Job, String> jobCrudUseCase, CrudUseCase<Department, Integer> departmentCrudUseCase) {
        this.employeeCrudUseCase = employeeCrudUseCase;
        this.jobCrudUseCase = jobCrudUseCase;
        this.departmentCrudUseCase = departmentCrudUseCase;
    }

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

        Employee employee = employeeCrudUseCase.find(employeeId);

        if (Objects.isNull(employee)) {
            Error.of("Employee").when(FIELD.NOT_FOUND).trows();
        }

        Job job = jobCrudUseCase.find(jobId);

        if (Objects.isNull(job)) {
            Error.of("Job").when(FIELD.NOT_FOUND).trows();
        }

        Department department = departmentCrudUseCase.find(departmentId);

        if (Objects.isNull(department)) {
            Error.of("Department").when(FIELD.NOT_FOUND).trows();
        }

        employee.setSalary(job.getMinSalary());
        employee.setDepartment(department);
        employee.setJob(job);

        employeeCrudUseCase.update(employee);

        logger.info("Employee '{}' promoted!", employeeId);
    }
}

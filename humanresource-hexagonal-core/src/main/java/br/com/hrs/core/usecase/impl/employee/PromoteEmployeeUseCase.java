package br.com.hrs.core.usecase.impl.employee;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.usecase.FindUseCase;
import br.com.hrs.core.usecase.UpdateUseCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class PromoteEmployeeUseCase {

    private Logger logger = LogManager.getLogger(PromoteEmployeeUseCase.class);

    private UpdateUseCase<Employee, Integer> employeeUpdateUseCase;
    private FindUseCase<Employee, Integer> employeeFindUseCase;
    private FindUseCase<Job, String> jobFindUseCase;
    private FindUseCase<Department, Integer> departmentFindUseCase;

    @Inject
    public PromoteEmployeeUseCase(UpdateUseCase<Employee, Integer> employeeUpdateUseCase, FindUseCase<Employee, Integer> employeeFindUseCase, FindUseCase<Job, String> jobFindUseCase, FindUseCase<Department, Integer> departmentFindUseCase ) {
        this.employeeUpdateUseCase = employeeUpdateUseCase;
        this.employeeFindUseCase = employeeFindUseCase;
        this.jobFindUseCase = jobFindUseCase;
        this.departmentFindUseCase = departmentFindUseCase;
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

        Employee employee = employeeFindUseCase.find(employeeId);

        if (Objects.isNull(employee)) {
            Error.of("Employee").when(FIELD.NOT_FOUND).trows();
        }

        Job job = jobFindUseCase.find(jobId);

        if (Objects.isNull(job)) {
            Error.of("Job").when(FIELD.NOT_FOUND).trows();
        }

        Department department = departmentFindUseCase.find(departmentId);

        if (Objects.isNull(department)) {
            Error.of("Department").when(FIELD.NOT_FOUND).trows();
        }

        employee.setSalary(job.getMinSalary());
        employee.setDepartment(department);
        employee.setJob(job);

        employeeUpdateUseCase.update(employee);

        logger.info("Employee '{}' promoted!", employeeId);
    }
}

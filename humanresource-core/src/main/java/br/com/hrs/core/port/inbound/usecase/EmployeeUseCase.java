package br.com.hrs.core.port.inbound.usecase;

import br.com.hrs.core.error.Validations;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.port.inbound.EmployeePromotion;
import br.com.hrs.core.port.outbound.DepartmentRepository;
import br.com.hrs.core.port.outbound.EmployeeRepository;
import br.com.hrs.core.port.outbound.JobRepository;
import br.com.hrs.core.service.EmployeePromotionService;

import javax.inject.Named;
import java.util.Objects;

@Named
public class EmployeeUseCase implements EmployeePromotion {

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
    public void promote(Integer employeeId, Integer jobId, Integer departmentId) {

        if (Objects.isNull(employeeId)) {
            Validations.mandatory("Employee ID");
        }

        if (Objects.isNull(jobId)) {
            Validations.mandatory("Job ID");
        }

        if (Objects.isNull(departmentId)) {
            Validations.mandatory("Department ID");
        }

        Employee employee = employeeRepository.get(employeeId);

        if (Objects.isNull(employee)) {
            Validations.notFound("Employee");
        }

        Job job = jobRepository.get(jobId);

        if (Objects.isNull(job)) {
            Validations.notFound("Job");
        }

        Department department = departmentRepository.get(departmentId);

        if (Objects.isNull(department)) {
            Validations.notFound("Department");
        }

        employee.setDepartment(department);
        employee.setJob(job);

        service.promote(employee, job, department);

        employeeRepository.save(employee);
    }
}

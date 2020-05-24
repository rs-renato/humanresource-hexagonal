package br.com.hrs.core.validator.employee;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.validator.Validator;

import java.util.Optional;

public interface PromotionValidator extends Validator<Employee> {

    @Override
    default void validate(Employee employee) {}

    default void validate(Optional<Employee> employee, Optional<Job> job, Optional<Department> department){
        validate(employee.orElse(null), job.orElse(null), department.orElse(null));
    }

    void validate(Employee employee, Job job, Department department);
}
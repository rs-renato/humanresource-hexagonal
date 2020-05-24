package br.com.hrs.core.validator.employee;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.validator.Validator;

public interface PromotionValidator extends Validator<Employee> {

    @Override
    default void validate(Employee employee) {
        // nothing to do
    }

    void validate(Employee employee, Job job, Department department);
}
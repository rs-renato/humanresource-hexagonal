package br.com.hrs.core.usecase.employee;

import br.com.hrs.core.model.Employee;
import br.com.hrs.core.usecase.CrudUseCase;

public interface EmployeeUseCase extends CrudUseCase<Employee, Integer> {

    void promote(Integer employeeId, String jobId, Integer departmentId);

}
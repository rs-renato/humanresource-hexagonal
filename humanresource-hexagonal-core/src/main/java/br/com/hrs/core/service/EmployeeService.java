package br.com.hrs.core.service;

import br.com.hrs.core.model.Employee;

public interface EmployeeService {
    Employee get(Integer employeeId);

    void promote(Integer employeeId, String jobId, Integer departmentId);
}

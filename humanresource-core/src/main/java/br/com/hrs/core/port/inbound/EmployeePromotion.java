package br.com.hrs.core.port.inbound;

import br.com.hrs.core.model.Employee;

public interface EmployeePromotion {
    Employee get(Integer employeeId);

    void promote(Integer employeeId, String jobId, Integer departmentId);
}

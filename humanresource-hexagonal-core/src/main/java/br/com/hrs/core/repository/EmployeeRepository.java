package br.com.hrs.core.repository;

import br.com.hrs.core.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public interface EmployeeRepository {

    Logger logger = LogManager.getLogger(EmployeeRepository.class);

    Employee get(Integer employeeId);

    void save(Employee employee);

    Collection<Employee> list();

    void delete(Integer employeeId);
}

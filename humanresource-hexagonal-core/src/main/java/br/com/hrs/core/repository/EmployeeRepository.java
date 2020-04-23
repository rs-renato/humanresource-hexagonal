package br.com.hrs.core.repository;

import br.com.hrs.core.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public interface EmployeeRepository {

    Logger logger = LogManager.getLogger(EmployeeRepository.class);

    Employee find(Integer employeeId);

    Integer save(Employee employee);

    boolean update(Employee employee);

    Collection<Employee> findAll();

    boolean delete(Integer employeeId);
}

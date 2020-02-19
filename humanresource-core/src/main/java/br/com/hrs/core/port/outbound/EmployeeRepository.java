package br.com.hrs.core.port.outbound;

import br.com.hrs.core.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface EmployeeRepository{

    Logger logger = LogManager.getLogger(EmployeeRepository.class);

    Employee get(Integer employeeId);

    @Transactional
    void save(Employee employee);
    Collection<Employee> list();

    @Transactional
    void delete(Integer employeeId);
}

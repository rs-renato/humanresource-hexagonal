package br.com.hrs.core.repository;

import br.com.hrs.core.model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public interface DepartmentRepository {

    Logger logger = LogManager.getLogger(DepartmentRepository.class);

    Department get(Integer employeeId);

    void save(Department employee);

    Collection<Department> list();

    void delete(Integer employeeId);
}

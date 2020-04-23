package br.com.hrs.core.repository;

import br.com.hrs.core.model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public interface DepartmentRepository {

    Logger logger = LogManager.getLogger(DepartmentRepository.class);

    Department find(Integer departmentId);

    Integer save(Department department);

    boolean update(Department department);

    Collection<Department> findAll();

    boolean delete(Integer departmentId);
}

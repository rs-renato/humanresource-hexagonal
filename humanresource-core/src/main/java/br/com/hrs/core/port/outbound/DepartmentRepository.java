package br.com.hrs.core.port.outbound;

import br.com.hrs.core.model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface DepartmentRepository {

    Logger logger = LogManager.getLogger(DepartmentRepository.class);

    Department get(Integer employeeId);

    @Transactional
    void save(Department employee);

    Collection<Department> list();

    @Transactional
    void delete(Integer employeeId);
}

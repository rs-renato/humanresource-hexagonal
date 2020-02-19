package br.com.hrs.core.port.outbound.mock;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.port.outbound.DepartmentRepository;

import javax.inject.Named;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Named
public class DepartmentMockRepository implements DepartmentRepository {

    private Map<Integer, Department> database = new HashMap<>();

    public DepartmentMockRepository() {
        this.database.put(1, new Department(1, "IT", null, null));
        this.database.put(2, new Department(2, "SP", null, null));
    }

    @Override
    public Department get(Integer departmentId) {
        logger.info("Fake database ->  Department get({}})", departmentId);
        return this.database.get(departmentId);
    }

    @Override
    public void save(Department department) {
        logger.info("Fake database ->  save({}})", department);
        this.database.put(department.getId(), department);
    }

    @Override
    public Collection<Department> list() {
        logger.info("Fake database ->  Collection<Department> list()");
        return this.database.values();
    }

    @Override
    public void delete(Integer departmentId) {
        logger.info("Fake database ->  delete({}})", departmentId);
        this.database.remove(departmentId);
    }
}

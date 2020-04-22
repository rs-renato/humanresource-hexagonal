package br.com.hrs.core.repository.impl;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.repository.DepartmentRepository;

import javax.inject.Named;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Named
public class DepartmentMockRepository implements DepartmentRepository {

    private Map<Integer, Department> database = new HashMap<>();

    public DepartmentMockRepository() {
        this.database.put(1, new Department(1, "IT", null));
        this.database.put(2, new Department(2, "SP", null));
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

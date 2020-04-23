package br.com.hrs.core.repository.impl;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.Repository;

import javax.inject.Named;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Named
public class DepartmentMockRepository extends Repository<Department, Integer> {

    private Map<Integer, Department> database = new HashMap<>();

    public DepartmentMockRepository() {

        Department dep01 = new Department.Builder()
                .id(10)
                .name("Administration")
                .manager(new Employee.Builder().id(200).build())
                .location(new Location.Builder().id(1700).build())
                .build();

        Department dep02 = new Department.Builder()
                .id(20)
                .name("Marketing")
                .manager(new Employee.Builder().id(201).build())
                .location(new Location.Builder().id(1800).build())
                .build();

        this.database.put(dep01.getId(), dep01);
        this.database.put(dep02.getId(), dep02);
    }

    @Override
    public Department find(Integer departmentId) {
        logger.info("Fake database -> find({}})", departmentId);
        return this.database.get(departmentId);
    }

    @Override
    public Integer save(Department department) {
        logger.info("Fake database ->  save({}})", department);
        this.database.put(department.getId(), department);
        return department.getId();
    }

    @Override
    public boolean update(Department department) {
        logger.info("Fake database ->  update({}})", department);
        return this.database.put(department.getId(), department) != null;
    }

    @Override
    public Collection<Department> findAll() {
        logger.info("Fake database ->  Collection<Department> findAll()");
        return this.database.values();
    }

    @Override
    public boolean delete(Integer departmentId) {
        logger.info("Fake database ->  delete({}})", departmentId);
        return this.database.remove(departmentId) != null;
    }

    @Override
    public boolean exists(Integer departmentId) {
        logger.info("Fake database ->  exists({}})", departmentId);
        return this.database.containsKey(departmentId);
    }
}

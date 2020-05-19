package br.com.hrs.core.repository.impl;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Location;

import javax.inject.Named;

@Named
public class DepartmentMockRepository extends MockRepository<Department, Integer> {

    @Override
    public void loadMockDatabase() {

        Department dep01 = new Department.Builder()
                .id(1)
                .name("Administration")
                .manager(new Employee.Builder().id(200).build())
                .location(new Location.Builder().id(1700).build())
                .build();

        Department dep02 = new Department.Builder()
                .id(2)
                .name("Marketing")
                .manager(new Employee.Builder().id(201).build())
                .location(new Location.Builder().id(1800).build())
                .build();

        this.database.put(dep01.getId(), dep01);
        this.database.put(dep02.getId(), dep02);
    }
}

package br.com.hrs.core.repository.mock;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.DepartmentRepository;

import javax.inject.Named;
import java.util.Arrays;
import java.util.Collection;

@Named
public class DepartmentMockRepository extends MockRepository<Department, Integer> implements DepartmentRepository {

    @Override
    public Collection<Department> buildCollection() {

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

        return Arrays.asList(dep01, dep02);
    }
}

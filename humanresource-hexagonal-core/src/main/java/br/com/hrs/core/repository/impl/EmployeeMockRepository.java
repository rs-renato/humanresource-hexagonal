package br.com.hrs.core.repository.impl;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.EmployeeRepository;

import javax.inject.Named;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Named
public class EmployeeMockRepository implements EmployeeRepository {

    private Map<Integer, Employee> database = new HashMap<>();

    public EmployeeMockRepository() {

        try {

                Employee employee01 = new Employee.Builder()
                        .id(100)
                        .firstName("Steven")
                        .lastName("King")
                        .email("SKING")
                        .phone("515.123.4567")
                        .hireDate(new SimpleDateFormat("dd.MM.yyyy").parse("17.06.2003"))
                        .job(new Job.Builder().id("AD_PRES").build())
                        .salary(24000f)
                        .commissionPercent(null)
                        .manager(new Employee.Builder().id(100).build())
                        .department(new Department.Builder().id(90).build())
                        .build();

                Employee employee02 = new Employee.Builder()
                        .id(100)
                        .firstName("Neena")
                        .lastName("Kochhar")
                        .email("NKOCHHAR")
                        .phone("515.123.4568")
                        .hireDate(new SimpleDateFormat("dd.MM.yyyy").parse("21.09.2005"))
                        .job(new Job.Builder().id("AD_VP").build())
                        .salary(17000f)
                        .commissionPercent(null)
                        .manager(new Employee.Builder().id(100).build())
                        .department(new Department.Builder().id(90).build())
                        .build();

            this.database.put(employee01.getId(), employee01);
            this.database.put(employee02.getId(), employee02);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee find(Integer employeeId) {
        logger.info("Fake database ->  find({}})", employeeId);
        return this.database.get(employeeId);
    }

    @Override
    public Integer save(Employee employee) {
        logger.info("Fake database ->  save({}})", employee);
        this.database.put(employee.getId(), employee);
        return employee.getId();
    }

    @Override
    public void update(Employee employee) {
        logger.info("Fake database ->  update({}})", employee);
        this.database.put(employee.getId(), employee);
    }

    @Override
    public Collection<Employee> findAll() {
        logger.info("Fake database ->  Collection<Employee> findAll()");
        return this.database.values();
    }

    @Override
    public void delete(Integer employeeId) {
        logger.info("Fake database ->  delete({}})", employeeId);
        this.database.remove(employeeId);
    }
}

package br.com.hrs.core.port.outbound.adapter.mock;

import br.com.hrs.core.model.Employee;
import br.com.hrs.core.port.outbound.EmployeeRepository;

import javax.inject.Named;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Named
public class EmployeeMockRepository implements EmployeeRepository {

    private Map<Integer, Employee> database = new HashMap<>();

    public EmployeeMockRepository() {
        this.database.put(1, new Employee(1, "renato@gmail", 1000f, null, null));
        this.database.put(2, new Employee(2, "camila@gmail", 5000f, null, null));
    }

    @Override
    public Employee get(Integer employeeId) {
        logger.info("Fake database ->  Employee get({}})", employeeId);
        return this.database.get(employeeId);
    }

    @Override
    public void save(Employee employee) {
        logger.info("Fake database ->  save({}})", employee);
        this.database.put(employee.getId(), employee);
    }

    @Override
    public Collection<Employee> list() {
        logger.info("Fake database ->  Collection<Employee> list()");
        return this.database.values();
    }

    @Override
    public void delete(Integer employeeId) {
        logger.info("Fake database ->  delete({}})", employeeId);
        this.database.remove(employeeId);
    }
}

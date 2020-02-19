package br.com.hrs.core.port.outbound.mock;

import br.com.hrs.core.model.Employee;
import br.com.hrs.core.port.outbound.EmployeeRepository;

import javax.inject.Named;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Named
public class EmployeeMockRepository implements EmployeeRepository {

    private Map<Integer, Employee> database = new HashMap<>();

    public EmployeeMockRepository() {
        this.database.put(1, new Employee(1,"renato","silva","renato@gmail","1234467890",new Date(),1000f,0.5f, null, null, null));
        this.database.put(2, new Employee(2,"camila","dias","camila@gmail","1234467890",new Date(),5000f,0.2f, null, null, null));
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

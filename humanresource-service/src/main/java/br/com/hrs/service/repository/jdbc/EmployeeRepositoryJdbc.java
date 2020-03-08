package br.com.hrs.service.repository.jdbc;

import br.com.hrs.core.model.Employee;
import br.com.hrs.core.port.outbound.EmployeeRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

@Named
public class EmployeeRepositoryJdbc implements EmployeeRepository {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public EmployeeRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Employee get(Integer employeeId) {
        return null;
    }

    @Override
    public void save(Employee employee) {

    }

    @Override
    public Collection<Employee> list() {
        return null;
    }

    @Override
    public void delete(Integer employeeId) {

    }
}

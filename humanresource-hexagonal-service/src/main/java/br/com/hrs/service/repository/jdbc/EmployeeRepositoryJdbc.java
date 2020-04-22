package br.com.hrs.service.repository.jdbc;

import br.com.hrs.core.model.Employee;
import br.com.hrs.core.repository.EmployeeRepository;
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
    public Employee find(Integer employeeId) {
        return null;
    }

    @Override
    public Integer save(Employee employee) {
        return null;
    }

    @Override
    public void update(Employee employee) {

    }

    @Override
    public Collection<Employee> findAll() {
        return null;
    }

    @Override
    public void delete(Integer employeeId) {

    }
}

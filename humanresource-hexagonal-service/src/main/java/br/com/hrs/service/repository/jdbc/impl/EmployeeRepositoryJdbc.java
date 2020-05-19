package br.com.hrs.service.repository.jdbc.impl;

import br.com.hrs.core.model.Employee;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.service.repository.jdbc.rowmapper.EmployeeRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.Objects;

@Named
public class EmployeeRepositoryJdbc implements Repository<Employee, Integer> {

    private JdbcTemplate jdbcTemplate;

    public EmployeeRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Employee find(Integer employeeId) {

        if (Objects.isNull(employeeId)) {
            return null;
        }

        String sql = "SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID = ?";
        Object[] param = new Object[]{employeeId};

        try {
            return jdbcTemplate.queryForObject(sql, param, new EmployeeRowMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Employee Id '{}' not found", employeeId);
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Employee save(Employee employee) {

        Integer savedId = null;

        if (Objects.nonNull(employee)) {

            String sql = "INSERT INTO EMPLOYEES (FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID) VALUES (?,?,?,?,?,?,?,?,?,?)";

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {

                PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setString(1, employee.getFirstName());
                stmt.setString(2, employee.getLastName());
                stmt.setString(3, employee.getEmail());
                stmt.setString(4, employee.getPhone());
                stmt.setDate(5, new Date(employee.getHireDate().getTime()));
                stmt.setString(6, employee.getJob().getId());
                stmt.setFloat(7, employee.getSalary());
                stmt.setFloat(8, employee.getCommissionPercent());
                stmt.setInt(9, employee.getManager().getId());
                stmt.setInt(10, employee.getDepartment().getId());

                return stmt;

            }, keyHolder);

            savedId = keyHolder.getKey().intValue();
            logger.debug("Returned saved ID {}", savedId);

            employee.setId(savedId);
            return employee;
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Employee employee) {

        if (Objects.nonNull(employee)) {

            String sql = "UPDATE EMPLOYEES SET FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, PHONE_NUMBER = ?, HIRE_DATE = ?, JOB_ID = ?, SALARY = ?, COMMISSION_PCT = ?, MANAGER_ID = ?, DEPARTMENT_ID = ? WHERE EMPLOYEE_ID = ?";

            Object[] param = new Object[]{employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPhone(), employee.getHireDate(), employee.getJob().getId(), employee.getSalary(), employee.getCommissionPercent(), employee.getManager().getId(), employee.getDepartment().getId(), employee.getId()};

            jdbcTemplate.update(sql, param);
        }
    }

    @Override
    public Collection<Employee> findAll() {
        String sql = "SELECT * FROM EMPLOYEES";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Integer employeeId) {
        if (Objects.nonNull(employeeId)) {

            String sql = "DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID = ?";

            return jdbcTemplate.update(sql, employeeId) > 0;
        }

        return false;
    }

    @Override
    public boolean exists(Integer employeeId) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM EMPLOYEES WHERE EMPLOYEE_ID = ?", new Object[]{employeeId}, Integer.class) > 0;
    }
}

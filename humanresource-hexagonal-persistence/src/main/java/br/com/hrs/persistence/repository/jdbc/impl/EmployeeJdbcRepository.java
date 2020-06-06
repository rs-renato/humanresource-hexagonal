package br.com.hrs.persistence.repository.jdbc.impl;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.repository.EmployeeRepository;
import br.com.hrs.persistence.repository.jdbc.rowmapper.EmployeeRowMapper;
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
import java.util.Optional;

@Named
public class EmployeeJdbcRepository implements EmployeeRepository {

    private JdbcTemplate jdbcTemplate;
    private final String REPOSITORY_NAME = getClass().getSimpleName();

    public EmployeeJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Employee> findById(Integer id) {

        logger.debug("{} ->  find({}})", REPOSITORY_NAME, id);

        if (Objects.isNull(id)) {
            Error.of("Employee ID").when(FIELD.MANDATORY).trows();
        }

        String sql = "SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID = ?";
        Object[] param = new Object[]{id};

        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, param, new EmployeeRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Employee Id '{}' not found", id);
            return Optional.empty();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Employee save(Employee employee) {

        logger.debug("{} ->  save({}})", REPOSITORY_NAME, employee);

        if (Objects.isNull(employee)) {
            Error.of("Employee").when(FIELD.MANDATORY).trows();
        }

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

        Integer savedId = keyHolder.getKey().intValue();
        logger.debug("Returned saved ID {}", savedId);

        employee.setId(savedId);
        
        return employee;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Employee employee) {

        logger.debug("{} ->  update({}})", REPOSITORY_NAME, employee);

        if (Objects.isNull(employee)) {
            Error.of("Employee").when(FIELD.MANDATORY).trows();
        }

        String sql = "UPDATE EMPLOYEES SET FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, PHONE_NUMBER = ?, HIRE_DATE = ?, JOB_ID = ?, SALARY = ?, COMMISSION_PCT = ?, MANAGER_ID = ?, DEPARTMENT_ID = ? WHERE EMPLOYEE_ID = ?";

        Object[] param = new Object[]{employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPhone(), employee.getHireDate(), employee.getJob().getId(), employee.getSalary(), employee.getCommissionPercent(), employee.getManager().getId(), employee.getDepartment().getId(), employee.getId()};

        jdbcTemplate.update(sql, param);
    }

    @Override
    public Collection<Employee> findAll() {
        logger.debug("{} -> findAll()", REPOSITORY_NAME);
        String sql = "SELECT * FROM EMPLOYEES";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        logger.debug("{} ->  delete({}})", REPOSITORY_NAME, id);

        if (Objects.isNull(id)) {
            Error.of("Employee ID").when(FIELD.MANDATORY).trows();
        }

        String sql = "DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID = ?";

        jdbcTemplate.update(sql, id);
        
    }

    @Override
    public boolean existsById(Integer id) {
        logger.debug("{} ->  exists({}})", REPOSITORY_NAME, id);

        if (Objects.isNull(id)) {
            Error.of("Employee ID").when(FIELD.MANDATORY).trows();
        }
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM EMPLOYEES WHERE EMPLOYEE_ID = ?", new Object[]{id}, Integer.class) > 0;
    }
}

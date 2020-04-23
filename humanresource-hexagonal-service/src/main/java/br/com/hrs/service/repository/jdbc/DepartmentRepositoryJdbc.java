package br.com.hrs.service.repository.jdbc;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.service.repository.jdbc.rowmapper.DepartmentRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.Objects;

@Named
public class DepartmentRepositoryJdbc extends Repository<Department, Integer> {

    private JdbcTemplate jdbcTemplate;

    public DepartmentRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Department find(Integer departmentId) {

        if (Objects.isNull(departmentId)) {
            return null;
        }

        String sql = "SELECT * FROM DEPARTMENTS WHERE DEPARTMENT_ID = ?";
        Object[] param = new Object[]{departmentId};

        try {
            return jdbcTemplate.queryForObject(sql, param, new DepartmentRowMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Department Id '{}' not found", departmentId);
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer save(Department department) {

        Integer savedId = null;

        if (Objects.nonNull(department)) {

            String sql = "INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID) VALUES (?,?,?)";

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {

                PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setString(1, department.getName());
                stmt.setInt(2, department.getManager().getId());
                stmt.setInt(3, department.getLocation().getId());

                return stmt;

            }, keyHolder);

            savedId = keyHolder.getKey().intValue();
            logger.debug("Returned saved ID {}", savedId);
        }

        return savedId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Department department) {

        if (Objects.nonNull(department)) {

            String sql = "UPDATE DEPARTMENTS SET DEPARTMENT_NAME = ?, MANAGER_ID = ? WHERE DEPARTMENT_ID = ?";
            Object[] param = new Object[]{department.getName(), department.getManager().getId(), department.getId()};

            return jdbcTemplate.update(sql, param) > 0;
        }

        return false;
    }

    @Override
    public Collection<Department> findAll() {
        String sql = "SELECT * FROM DEPARTMENTS";
        return jdbcTemplate.query(sql, new DepartmentRowMapper());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Integer departmentId) {
        if (Objects.nonNull(departmentId)) {

            String sql = "DELETE FROM DEPARTMENTS WHERE DEPARTMENT_ID = ?";

            return jdbcTemplate.update(sql, departmentId) > 0;
        }

        return false;
    }

    @Override
    public boolean exists(Integer departmentId) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEPARTMENTS WHERE DEPARTMENT_ID = ?", new Object[]{departmentId}, Integer.class) > 0;
    }
}

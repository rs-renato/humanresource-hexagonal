package br.com.hrs.service.repository.jdbc;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.repository.DepartmentRepository;
import br.com.hrs.core.repository.EmployeeRepository;
import br.com.hrs.core.repository.LocationRepository;
import br.com.hrs.service.repository.jdbc.rowmapper.DepartmentRowMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.Objects;

@Named
public class DepartmentRepositoryJdbc implements DepartmentRepository {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public DepartmentRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Department find(Integer departmentId) {

        if (Objects.isNull(departmentId)) {
            return null;
        }

        String sql = "select * from departments where department_id = ?";
        Object[] param = new Object[]{departmentId};

        return jdbcTemplate.queryForObject(sql, param, new DepartmentRowMapper());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer save(Department department) {
        if (Objects.nonNull(department)) {

            String sql = "insert into departments values (?,?,?)";

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"department_id"});
                stmt.setString(1, department.getName());
                stmt.setInt(2, department.getManager().getId());
                stmt.setInt(3, department.getLocation().getId());
                return stmt;
            }, keyHolder);

            return keyHolder.getKey().intValue();
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Department department) {

        if (Objects.nonNull(department)) {

            String sql = "update departments set department_name = ?, manager_id = ? where department_id_id = ?";
            Object[] param = new Object[]{department.getName(), department.getManager().getId(), department.getId()};

            jdbcTemplate.update(sql, param);
        }
    }

    @Override
    public Collection<Department> findAll() {
        String sql = "select * from departments";
        return jdbcTemplate.query(sql, new DepartmentRowMapper());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer departmentId) {
        if (Objects.nonNull(departmentId)) {
            String sql = "delete from departments where department_id = ?";
            jdbcTemplate.update(sql, departmentId);
        }
    }
}

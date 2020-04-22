package br.com.hrs.service.repository.jdbc;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.repository.DepartmentRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
import java.util.Objects;

@Named
public class DepartmentRepositoryJdbc implements DepartmentRepository {

    private JdbcTemplate jdbcTemplate;
    private EmployeeRepositoryJdbc employeeRepository;

    @Inject
    @Lazy
    public DepartmentRepositoryJdbc(EmployeeRepositoryJdbc employeeRepository, JdbcTemplate jdbcTemplate) {
        this.employeeRepository = employeeRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Department get(Integer departmentId) {

        if (Objects.isNull(departmentId)) {
            return null;
        }

        String sql = "select * from departments where department_id = ?";
        Object[] param = new Object[]{departmentId};

        RowMapper<Department> rowMapper = (rs, rowNum) -> {
            String name = rs.getString(2);
            Integer managerID = rs.getInt(3);
            return new Department(departmentId, name, employeeRepository.get(managerID));
        };

        return jdbcTemplate.queryForObject(sql, param, rowMapper);
    }

    @Override
    @Transactional
    public void save(Department department) {
        if (Objects.nonNull(department)) {

            String sql = null;
            Object[] param = null;

            if (Objects.nonNull(department.getId())) {
                sql = "insert into departments values (?,?,?)";
                param = new Object[]{department.getId(), department.getName(), department.getManager().getId()};
            } else {
                sql = "update departments set department_name = ?, manager_id = ? where department_id_id = ?";
                param = new Object[]{department.getName(), department.getManager().getId(), department.getId()};
            }
            jdbcTemplate.update(sql, param);
        }
    }

    @Override
    public Collection<Department> list() {
        String sql = "select * from departments";
        RowMapper<Department> rowMapper = (rs, rowNum) -> {
            Integer departmentId = rs.getInt(1);
            String name = rs.getString(2);
            Integer managerID = rs.getInt(3);
            return new Department(departmentId, name, employeeRepository.get(managerID));
        };

        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    @Transactional
    public void delete(Integer departmentId) {
        if (Objects.nonNull(departmentId)) {
            String sql = "delete from departments where department_id = ?";
            jdbcTemplate.update(sql, departmentId);
        }
    }
}

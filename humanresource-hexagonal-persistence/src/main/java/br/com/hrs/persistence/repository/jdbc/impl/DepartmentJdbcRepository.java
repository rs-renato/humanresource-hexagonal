package br.com.hrs.persistence.repository.jdbc.impl;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.repository.DepartmentRepository;
import br.com.hrs.core.repository.filter.Filter;
import br.com.hrs.core.repository.filter.QueryFilter;
import br.com.hrs.core.repository.pagination.Pagination;
import br.com.hrs.persistence.repository.jdbc.rowmapper.DepartmentRowMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Named
public class DepartmentJdbcRepository implements DepartmentRepository {

    private static Logger logger = LogManager.getLogger(DepartmentJdbcRepository.class);

    private JdbcTemplate jdbcTemplate;

    public DepartmentJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long count() {
        String sql = "SELECT COUNT(*) FROM DEPARTMENTS";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public Optional<Department> findById(Integer id) {

        logger.debug("find({}})", id);

        if (Objects.isNull(id)) {
            Error.of("Department ID").when(FIELD.MANDATORY).trows();
        }

        String sql = "SELECT * FROM DEPARTMENTS WHERE DEPARTMENT_ID = ?";
        Object[] param = new Object[]{id};

        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, param, new DepartmentRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Department Id '{}' not found", id);
            return Optional.empty();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Department save(Department department) {

        logger.debug("save({}})", department);

        if (Objects.isNull(department)) {
            Error.of("Department").when(FIELD.MANDATORY).trows();
        }

        String sql = "INSERT INTO DEPARTMENTS (DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID) VALUES (?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

            PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, department.getName());
            stmt.setInt(2, department.getManager().getId());
            stmt.setInt(3, department.getLocation().getId());

            return stmt;

        }, keyHolder);

        Integer savedId = keyHolder.getKey().intValue();
        logger.debug("Returned saved ID {}", savedId);

        department.setId(savedId);

        return department;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Department department) {

        logger.debug("update({}})", department);

        if (Objects.isNull(department)) {
            Error.of("Department").when(FIELD.MANDATORY).trows();
        }

        String sql = "UPDATE DEPARTMENTS SET DEPARTMENT_NAME = ?, MANAGER_ID = ? WHERE DEPARTMENT_ID = ?";
        Object[] param = new Object[]{department.getName(), department.getManager().getId(), department.getId()};

        jdbcTemplate.update(sql, param);
    }

    @Override
    public List<Department> findAll() {
        logger.debug("findAll()");
        String sql = "SELECT * FROM DEPARTMENTS";
        return jdbcTemplate.query(sql, new DepartmentRowMapper());
    }

    @Override
    public List<Department> findAll(Pagination pagination) {
        logger.debug("paginated findAll()");

        String sql = "SELECT * FROM DEPARTMENTS OFFSET ? ROWS FETCH FIRST ? ROWS ONLY";

        return jdbcTemplate.query(sql, new Object[]{pagination.getPage() * pagination.getSize(), pagination.getSize()}, new DepartmentRowMapper());
    }

    @Override
    public List<Department> findAll(Filter<Department> filter) {
        logger.debug("filtered findAll()");

        QueryFilter<Department> queryFilter = filter.filterToQuery();

        StringBuilder query = new StringBuilder("SELECT * FROM DEPARTMENTS")
                .append(" WHERE ")
                .append(queryFilter.getSql());

        return jdbcTemplate.query(query.toString(), queryFilter.getValues(), new DepartmentRowMapper());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        logger.debug("delete({}})", id);

        if (Objects.isNull(id)) {
            Error.of("Department ID").when(FIELD.MANDATORY).trows();
        }

        String sql = "DELETE FROM DEPARTMENTS WHERE DEPARTMENT_ID = ?";

        jdbcTemplate.update(sql, id);
        
    }

    @Override
    public boolean existsById(Integer id) {
        logger.debug("exists({}})", id);

        if (Objects.isNull(id)) {
            Error.of("Department ID").when(FIELD.MANDATORY).trows();
        }
        
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEPARTMENTS WHERE DEPARTMENT_ID = ?", new Object[]{id}, Integer.class) > 0;
    }

    @Override
    public boolean existsByName(String name) {
        logger.debug("existsByName({}})", name);

        if (Objects.isNull(name)) {
            Error.of("Department Name").when(FIELD.MANDATORY).trows();
        }

        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DEPARTMENTS WHERE DEPARTMENT_NAME = ?", new Object[]{name}, Integer.class) > 0;
    }
}

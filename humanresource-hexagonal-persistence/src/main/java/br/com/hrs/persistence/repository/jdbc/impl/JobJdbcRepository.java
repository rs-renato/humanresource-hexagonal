package br.com.hrs.persistence.repository.jdbc.impl;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.JobRepository;
import br.com.hrs.core.repository.filter.Filter;
import br.com.hrs.core.repository.filter.QueryFilter;
import br.com.hrs.core.repository.pagination.Pagination;
import br.com.hrs.persistence.repository.jdbc.rowmapper.JobRowMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Named
public class JobJdbcRepository implements JobRepository {

    private static Logger logger = LogManager.getLogger(JobJdbcRepository.class);
    private JdbcTemplate jdbcTemplate;

    public JobJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long count() {
        String sql = "SELECT COUNT(*) FROM JOBS";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public Optional<Job> findById(String id) {

        logger.debug("find({}})", id);

        if (Objects.isNull(id)) {
            Error.of("Job ID").when(FIELD.MANDATORY).trows();
        }

        String sql = "SELECT * FROM JOBS WHERE JOB_ID = ?";
        Object[] param = new Object[]{id};

        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, param, new JobRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Job Id '{}' not found", id);
            return Optional.empty();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Job save(Job job) {
        
        logger.debug("save({}})", job);

        if (Objects.isNull(job)) {
            Error.of("Job").when(FIELD.MANDATORY).trows();
        }

        String sql = "INSERT INTO JOBS VALUES (?,?,?,?)";
        Object[] param = new Object[]{job.getId(), job.getTitle(), job.getMinSalary(), job.getMaxSalary()};

        jdbcTemplate.update(sql, param);

        return job;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Job job) {

        logger.debug("update({}})", job);

        if (Objects.isNull(job)) {
             Error.of("Job").when(FIELD.MANDATORY).trows();
        }
        
        String sql = "UPDATE JOBS SET JOB_TITLE = ?, MIN_SALARY = ?, MAX_SALARY = ? WHERE JOB_ID = ?";
        Object[] param = new Object[]{job.getTitle(), job.getMinSalary(), job.getMaxSalary(), job.getId()};

        jdbcTemplate.update(sql, param);
    }

    @Override
    public List<Job> findAll() {
        logger.debug("findAll()");

        String sql = "SELECT * FROM JOBS";
        return jdbcTemplate.query(sql, new JobRowMapper());
    }

    @Override
    public List<Job> findAll(Pagination pagination) {
        logger.debug("paginated findAll()");

        String sql = "SELECT * FROM JOBS OFFSET ? ROWS FETCH FIRST ? ROWS ONLY";

        return jdbcTemplate.query(sql, new Object[]{pagination.getPage() * pagination.getSize(), pagination.getSize()}, new JobRowMapper());
    }

    @Override
    public List<Job> findAll(Filter<Job> filter) {
        logger.debug("filtered findAll()");

        QueryFilter<Job> queryFilter = filter.filterToQuery();

        StringBuilder query = new StringBuilder("SELECT * FROM JOBS")
                .append(" WHERE ")
                .append(queryFilter.getSql());

        return jdbcTemplate.query(query.toString(), queryFilter.getValues(), new JobRowMapper());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        logger.debug("delete({}})", id);

        if (Objects.isNull(id)) {
             Error.of("Job").when(FIELD.MANDATORY).trows();
        }
        
        String sql = "DELETE FROM JOBS WHERE JOB_ID = ?";
        jdbcTemplate.update(sql, id);
        
    }

    @Override
    public boolean existsById(String id) {
        logger.debug("exists({}})", id);

        if (Objects.isNull(id)) {
             Error.of("Job ID").when(FIELD.MANDATORY).trows();
        }
        
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM JOBS WHERE JOB_ID = ?", new Object[]{id}, Integer.class) > 0;
    }
}

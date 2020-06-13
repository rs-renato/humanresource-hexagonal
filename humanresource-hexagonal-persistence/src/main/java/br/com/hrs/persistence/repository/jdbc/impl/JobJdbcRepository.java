package br.com.hrs.persistence.repository.jdbc.impl;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.JobRepository;
import br.com.hrs.persistence.repository.jdbc.rowmapper.JobRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Named
public class JobJdbcRepository implements JobRepository {

    private JdbcTemplate jdbcTemplate;
    private final String REPOSITORY_NAME = getClass().getSimpleName();

    public JobJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Job> findById(String id) {

        logger.debug("{} ->  find({}})", REPOSITORY_NAME, id);

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
        
        logger.debug("{} ->  save({}})", REPOSITORY_NAME, job);

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

        logger.debug("{} ->  update({}})", REPOSITORY_NAME, job);

        if (Objects.isNull(job)) {
             Error.of("Job").when(FIELD.MANDATORY).trows();
        }
        
        String sql = "UPDATE JOBS SET JOB_TITLE = ?, MIN_SALARY = ?, MAX_SALARY = ? WHERE JOB_ID = ?";
        Object[] param = new Object[]{job.getTitle(), job.getMinSalary(), job.getMaxSalary(), job.getId()};

        jdbcTemplate.update(sql, param);
    }

    @Override
    public List<Job> findAll() {
        logger.debug("{} -> findAll()", REPOSITORY_NAME);

        String sql = "SELECT * FROM JOBS";
        return jdbcTemplate.query(sql, new JobRowMapper());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        logger.debug("{} ->  delete({}})", REPOSITORY_NAME, id);

        if (Objects.isNull(id)) {
             Error.of("Job").when(FIELD.MANDATORY).trows();
        }
        
        String sql = "DELETE FROM JOBS WHERE JOB_ID = ?";
        jdbcTemplate.update(sql, id);
        
    }

    @Override
    public boolean existsById(String id) {
        logger.debug("{} ->  exists({}})", REPOSITORY_NAME, id);

        if (Objects.isNull(id)) {
             Error.of("Job ID").when(FIELD.MANDATORY).trows();
        }
        
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM JOBS WHERE JOB_ID = ?", new Object[]{id}, Integer.class) > 0;
    }
}

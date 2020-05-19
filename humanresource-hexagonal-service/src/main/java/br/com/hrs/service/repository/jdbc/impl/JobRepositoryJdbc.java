package br.com.hrs.service.repository.jdbc.impl;

import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.service.repository.jdbc.rowmapper.JobRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.Collection;
import java.util.Objects;

@Named
public class JobRepositoryJdbc implements Repository<Job, String> {

    private JdbcTemplate jdbcTemplate;

    public JobRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Job find(String jobId) {

        if (Objects.isNull(jobId)) {
            return null;
        }

        String sql = "SELECT * FROM JOBS WHERE JOB_ID = ?";
        Object[] param = new Object[]{jobId};

        try {
            return jdbcTemplate.queryForObject(sql, param, new JobRowMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Job Id '{}' not found", jobId);
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Job save(Job job) {
        if (Objects.nonNull(job)) {

            String sql = "INSERT INTO JOBS VALUES (?,?,?,?)";
            Object[] param = new Object[]{job.getId(), job.getTitle(), job.getMinSalary(), job.getMaxSalary()};

            jdbcTemplate.update(sql, param);

            return job;
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Job job) {
        if (Objects.nonNull(job)) {
            String sql = "UPDATE JOBS SET JOB_TITLE = ?, MIN_SALARY = ?, MAX_SALARY = ? WHERE JOB_ID = ?";
            Object[] param = new Object[]{job.getTitle(), job.getMinSalary(), job.getMaxSalary(), job.getId()};

            jdbcTemplate.update(sql, param);
        }
    }

    @Override
    public Collection<Job> findAll() {
        String sql = "SELECT * FROM JOBS";
        return jdbcTemplate.query(sql, new JobRowMapper());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(String jobId) {
        if (Objects.nonNull(jobId)) {
            String sql = "DELETE FROM JOBS WHERE JOB_ID = ?";
            return jdbcTemplate.update(sql, jobId) > 0;
        }

        return false;
    }

    @Override
    public boolean exists(String jobId) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM JOBS WHERE JOB_ID = ?", new Object[]{jobId}, Integer.class) > 0;
    }
}

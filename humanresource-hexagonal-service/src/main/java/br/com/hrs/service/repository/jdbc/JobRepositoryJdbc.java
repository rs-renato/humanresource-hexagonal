package br.com.hrs.service.repository.jdbc;

import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.JobRepository;
import br.com.hrs.service.repository.jdbc.rowmapper.JobRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
import java.util.Objects;

@Named
public class JobRepositoryJdbc implements JobRepository {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public JobRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Job find(String jobId) {

        if (Objects.isNull(jobId)) {
            return null;
        }

        String sql = "select * from jobs where job_id = ?";
        Object[] param = new Object[]{jobId};

        try {
            return jdbcTemplate.queryForObject(sql, param, new JobRowMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Job Id '{}' not found", jobId);
            return null;
        }
    }

    @Override
    @Transactional
    public String save(Job job) {
        if (Objects.nonNull(job)) {

            String sql = "insert into jobs values (?,?,?,?)";
            Object[] param = new Object[]{job.getId(), job.getTitle(), job.getMinSalary(), job.getMaxSalary()};

            jdbcTemplate.update(sql, param);

            return job.getId();
        }

        return null;
    }

    @Override
    public void update(Job job) {
        if (Objects.nonNull(job)) {
            String sql = "update jobs set job_title = ?, min_salary = ?, max_salary = ? where job_id = ?";
            Object[] param = new Object[]{job.getTitle(), job.getMinSalary(), job.getMaxSalary(), job.getId()};

            jdbcTemplate.update(sql, param);
        }
    }

    @Override
    public Collection<Job> findAll() {
        String sql = "select * from jobs";
        return jdbcTemplate.query(sql, new JobRowMapper());
    }

    @Override
    @Transactional
    public void delete(String jobId) {
        if (Objects.nonNull(jobId)) {
            String sql = "delete from jobs where job_id = ?";
            jdbcTemplate.update(sql, jobId);
        }
    }

    @Override
    public boolean exists(String jobId) {
        return jdbcTemplate.queryForObject("select count(*) from jobs where job_id = ?", new Object[]{jobId}, Integer.class) > 0;
    }
}

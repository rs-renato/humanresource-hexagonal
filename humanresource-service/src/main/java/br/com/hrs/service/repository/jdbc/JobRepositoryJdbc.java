package br.com.hrs.service.repository.jdbc;

import br.com.hrs.core.model.Job;
import br.com.hrs.core.port.outbound.JobRepository;
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
    public Job get(String jobId) {

        if (Objects.isNull(jobId)) {
            return null;
        }

        String sql = "select * from jobs where job_id = ?";
        Object[] param = new Object[]{jobId};

        RowMapper<Job> rowMapper = (rs, rowNum) -> {
            String id = rs.getString(1);
            String title = rs.getString(2);
            Float minSalary = rs.getFloat(3);
            Float maxSalary = rs.getFloat(4);

            return new Job(id, title, minSalary, maxSalary);
        };

        try {
            return jdbcTemplate.queryForObject(sql, param, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Job Id '{}' not found", jobId);
            return null;
        }
    }

    @Override
    @Transactional
    public void save(Job job) {
        if (Objects.nonNull(job)) {

            String sql = null;
            Object[] param = null;

            boolean exists = exists(job.getId());

            if (!exists) {
                sql = "insert into jobs values (?,?,?,?)";
                param = new Object[]{job.getId(), job.getTitle(), job.getMinSalary(), job.getMaxSalary()};
            } else if (exists(job.getId())) {
                sql = "update jobs set job_title = ?, min_salary = ?, max_salary = ? where job_id = ?";
                param = new Object[]{job.getTitle(), job.getMinSalary(), job.getMaxSalary(), job.getId()};
            }

            jdbcTemplate.update(sql, param);
        }
    }

    @Override
    public Collection<Job> list() {
        String sql = "select * from jobs";

        RowMapper<Job> rowMapper = (rs, rowNum) -> {
            String id = rs.getString(1);
            String title = rs.getString(2);
            Float minSalary = rs.getFloat(3);
            Float maxSalary = rs.getFloat(4);

            return new Job(id, title, minSalary, maxSalary);
        };

        return jdbcTemplate.query(sql, rowMapper);
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

package br.com.hrs.service.repository.jdbc.rowmapper;

import br.com.hrs.core.model.Job;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JobRowMapper implements RowMapper<Job> {

    @Override
    public Job mapRow(ResultSet rs, int i) throws SQLException {
        return new Job.Builder()
                .id(rs.getString("JOB_ID"))
                .title(rs.getString("JOB_TITLE"))
                .minSalary(rs.getFloat("MIN_SALARY"))
                .maxSalay(rs.getFloat("MAX_SALARY"))
            .build();
    }
}

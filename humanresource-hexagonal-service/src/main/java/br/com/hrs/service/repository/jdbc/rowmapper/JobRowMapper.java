package br.com.hrs.service.repository.jdbc.rowmapper;

import br.com.hrs.core.model.Job;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JobRowMapper implements RowMapper<Job> {

    @Override
    public Job mapRow(ResultSet rs, int i) throws SQLException {
        String id = rs.getString(1);
        String title = rs.getString(2);
        Float minSalary = rs.getFloat(3);
        Float maxSalary = rs.getFloat(4);
        return new Job(id, title, minSalary, maxSalary);
    }
}

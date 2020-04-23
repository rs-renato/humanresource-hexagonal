package br.com.hrs.service.repository.jdbc.rowmapper;

import br.com.hrs.core.model.Region;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegionRowMapper implements RowMapper<Region> {
    @Override
    public Region mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Region.Builder()
                    .id(rs.getInt("REGION_ID"))
                    .name(rs.getString("REGION_NAME"))
                .build();
    }
}

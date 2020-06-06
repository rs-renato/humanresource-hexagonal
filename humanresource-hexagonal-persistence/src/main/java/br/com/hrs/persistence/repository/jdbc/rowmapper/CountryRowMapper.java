package br.com.hrs.persistence.repository.jdbc.rowmapper;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Region;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryRowMapper implements RowMapper<Country> {
    @Override
    public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Country.Builder()
                        .id(rs.getString("COUNTRY_ID"))
                        .name(rs.getString("COUNTRY_NAME"))
                        .region(new Region.Builder().id(rs.getInt("REGION_ID")).build())
                    .build();
    }
}

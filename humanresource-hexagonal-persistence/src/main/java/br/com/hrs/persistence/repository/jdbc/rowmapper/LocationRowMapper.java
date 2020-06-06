package br.com.hrs.persistence.repository.jdbc.rowmapper;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Location;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationRowMapper implements RowMapper<Location> {
    @Override
    public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Location.Builder()
                    .id(rs.getInt("LOCATION_ID"))
                    .address(rs.getString("STREET_ADDRESS"))
                    .postalCode(rs.getString("POSTAL_CODE"))
                    .city(rs.getString("CITY"))
                    .state(rs.getString("STATE_PROVINCE"))
                    .country(new Country.Builder().id(rs.getString("COUNTRY_ID")).build())
                .build();
    }
}
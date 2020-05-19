package br.com.hrs.service.repository.jdbc.impl;

import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.service.repository.jdbc.rowmapper.LocationRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.Objects;

@Named
public class LocationRepositoryJdbc implements Repository<Location, Integer> {

    private JdbcTemplate jdbcTemplate;

    public LocationRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Location find(Integer locationId) {

        if (Objects.isNull(locationId)) {
            return null;
        }

        String sql = "SELECT * FROM LOCATIONS WHERE LOCATION_ID = ?";
        Object[] param = new Object[]{locationId};

        try {
            return jdbcTemplate.queryForObject(sql, param, new LocationRowMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Location Id '{}' not found", locationId);
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Location save(Location location) {

        Integer savedId = null;

        if (Objects.nonNull(location)) {

            String sql = "INSERT INTO LOCATIONS (STREET_ADDRESS, POSTAL_CODE, CITY, STATE_PROVINCE, COUNTRY_ID) VALUES (?,?,?,?,?)";

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {

                PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setString(1, location.getAddress());
                stmt.setString(2, location.getPostalCode());
                stmt.setString(3, location.getCity());
                stmt.setString(4, location.getState());
                stmt.setString(5, location.getCountry().getId());

                return stmt;

            }, keyHolder);

            savedId = keyHolder.getKey().intValue();
            logger.debug("Returned saved ID {}", savedId);

            location.setId(savedId);
            return location;
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Location location) {

        if (Objects.nonNull(location)) {

            String sql = "UPDATE LOCATIONS SET STREET_ADDRESS = ?, POSTAL_CODE = ?, CITY = ?, STATE_PROVINCE = ?, COUNTRY_ID = ?  WHERE LOCATION_ID = ?";
            Object[] param = new Object[]{location.getAddress(), location.getPostalCode(), location.getCity(), location.getState(), location.getCountry().getId(), location.getId()};

            jdbcTemplate.update(sql, param);
        }
    }

    @Override
    public Collection<Location> findAll() {
        String sql = "SELECT * FROM LOCATIONS";
        return jdbcTemplate.query(sql, new LocationRowMapper());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Integer locationId) {
        if (Objects.nonNull(locationId)) {

            String sql = "DELETE FROM LOCATIONS WHERE LOCATION_ID = ?";

            return jdbcTemplate.update(sql, locationId) > 0;
        }

        return false;
    }

    @Override
    public boolean exists(Integer locationId) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM LOCATIONS WHERE LOCATION_ID = ?", new Object[]{locationId}, Integer.class) > 0;
    }
}

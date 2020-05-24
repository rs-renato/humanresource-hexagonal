package br.com.hrs.service.repository.jdbc.impl;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
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
public class LocationJdbcRepository implements Repository<Location, Integer> {

    private JdbcTemplate jdbcTemplate;
    private final String REPOSITORY_NAME = getClass().getSimpleName();

    public LocationJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Location find(Integer id) {

        logger.debug("{} ->  find({}})", REPOSITORY_NAME, id);

        if (Objects.isNull(id)) {
            Error.of("Location ID").when(FIELD.MANDATORY).trows();
        }

        String sql = "SELECT * FROM LOCATIONS WHERE LOCATION_ID = ?";
        Object[] param = new Object[]{id};

        try {
            return jdbcTemplate.queryForObject(sql, param, new LocationRowMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Location Id '{}' not found", id);
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Location save(Location location) {
        
        logger.debug("{} ->  save({}})", REPOSITORY_NAME, location);

        if (Objects.isNull(location)) {
            Error.of("Location").when(FIELD.MANDATORY).trows();
        }

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

        Integer savedId = keyHolder.getKey().intValue();
        logger.debug("Returned saved ID {}", savedId);

        location.setId(savedId);
        return location;
       
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Location location) {

        logger.debug("{} ->  update({}})", REPOSITORY_NAME, location);

        if (Objects.isNull(location)) {
            Error.of("Location").when(FIELD.MANDATORY).trows();
        }

        String sql = "UPDATE LOCATIONS SET STREET_ADDRESS = ?, POSTAL_CODE = ?, CITY = ?, STATE_PROVINCE = ?, COUNTRY_ID = ?  WHERE LOCATION_ID = ?";
        Object[] param = new Object[]{location.getAddress(), location.getPostalCode(), location.getCity(), location.getState(), location.getCountry().getId(), location.getId()};

        jdbcTemplate.update(sql, param);
        
    }

    @Override
    public Collection<Location> findAll() {
        logger.debug("{} -> findAll()", REPOSITORY_NAME);

        String sql = "SELECT * FROM LOCATIONS";
        return jdbcTemplate.query(sql, new LocationRowMapper());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        logger.debug("{} ->  delete({}})", REPOSITORY_NAME, id);

        if (Objects.isNull(id)) {
            Error.of("Location").when(FIELD.MANDATORY).trows();
        }

        String sql = "DELETE FROM LOCATIONS WHERE LOCATION_ID = ?";

        jdbcTemplate.update(sql, id);
        
    }

    @Override
    public boolean exists(Integer id) {
        logger.debug("{} ->  exists({}})", REPOSITORY_NAME, id);

        if (Objects.isNull(id)) {
            Error.of("Location ID").when(FIELD.MANDATORY).trows();
        }
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM LOCATIONS WHERE LOCATION_ID = ?", new Object[]{id}, Integer.class) > 0;
    }
}

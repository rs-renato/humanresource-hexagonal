package br.com.hrs.persistence.repository.jdbc.impl;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.LocationRepository;
import br.com.hrs.core.repository.pagination.Pagination;
import br.com.hrs.persistence.repository.jdbc.rowmapper.LocationRowMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Named
public class LocationJdbcRepository implements LocationRepository {

    private static Logger logger = LogManager.getLogger(LocationJdbcRepository.class);
    private JdbcTemplate jdbcTemplate;

    public LocationJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long count() {
        String sql = "SELECT COUNT(*) FROM LOCATIONS";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public Optional<Location> findById(Integer id) {

        logger.debug("find({}})", id);

        if (Objects.isNull(id)) {
            Error.of("Location ID").when(FIELD.MANDATORY).trows();
        }

        String sql = "SELECT * FROM LOCATIONS WHERE LOCATION_ID = ?";
        Object[] param = new Object[]{id};

        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, param, new LocationRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Location Id '{}' not found", id);
            return Optional.empty();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Location save(Location location) {
        
        logger.debug("save({}})", location);

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

        logger.debug("update({}})", location);

        if (Objects.isNull(location)) {
            Error.of("Location").when(FIELD.MANDATORY).trows();
        }

        String sql = "UPDATE LOCATIONS SET STREET_ADDRESS = ?, POSTAL_CODE = ?, CITY = ?, STATE_PROVINCE = ?, COUNTRY_ID = ?  WHERE LOCATION_ID = ?";
        Object[] param = new Object[]{location.getAddress(), location.getPostalCode(), location.getCity(), location.getState(), location.getCountry().getId(), location.getId()};

        jdbcTemplate.update(sql, param);
        
    }

    @Override
    public List<Location> findAll() {
        logger.debug("findAll()");

        String sql = "SELECT * FROM LOCATIONS";
        return jdbcTemplate.query(sql, new LocationRowMapper());
    }

    @Override
    public List<Location> findAll(Pagination pagination) {
        logger.debug("paginated findAll()");

        String sql = "SELECT * FROM LOCATIONS OFFSET ? ROWS FETCH FIRST ? ROWS ONLY";

        return jdbcTemplate.query(sql, new Object[]{pagination.getPage() * pagination.getSize(), pagination.getSize()}, new LocationRowMapper());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        logger.debug("delete({}})", id);

        if (Objects.isNull(id)) {
            Error.of("Location").when(FIELD.MANDATORY).trows();
        }

        String sql = "DELETE FROM LOCATIONS WHERE LOCATION_ID = ?";

        jdbcTemplate.update(sql, id);
        
    }

    @Override
    public boolean existsById(Integer id) {
        logger.debug("exists({}})", id);

        if (Objects.isNull(id)) {
            Error.of("Location ID").when(FIELD.MANDATORY).trows();
        }
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM LOCATIONS WHERE LOCATION_ID = ?", new Object[]{id}, Integer.class) > 0;
    }
}

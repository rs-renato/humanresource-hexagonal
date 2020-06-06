package br.com.hrs.persistence.repository.jdbc.impl;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.RegionRepository;
import br.com.hrs.persistence.repository.jdbc.rowmapper.RegionRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Named
public class RegionJdbcRepository implements RegionRepository {

    private JdbcTemplate jdbcTemplate;
    private final String REPOSITORY_NAME = getClass().getSimpleName();

    public RegionJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Region> findById(Integer id) {
        logger.debug("{} ->  find({}})", REPOSITORY_NAME, id);

        if (Objects.isNull(id)) {
            Error.of("Region ID").when(FIELD.MANDATORY).trows();
        }

        String sql = "SELECT * FROM REGIONS WHERE REGION_ID = ?";

        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, new Object[]{id}, new RegionRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Region Id '{}' not found", id);
            return Optional.empty();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Region save(Region region) {

        logger.debug("{} ->  save({}})", REPOSITORY_NAME, region);

        if (Objects.isNull(region)) {
            Error.of("Region").when(FIELD.MANDATORY).trows();
        }

        String sql = "INSERT INTO REGIONS (REGION_NAME) VALUES (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

            PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, region.getName());

            return stmt;

        }, keyHolder);

        Integer savedId = keyHolder.getKey().intValue();
        logger.debug("Returned saved ID {}", savedId);
        region.setId(savedId);
        return region;
       
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Region region) {

        logger.debug("{} ->  update({}})", REPOSITORY_NAME, region);

        if (Objects.isNull(region)) {
            Error.of("Region").when(FIELD.MANDATORY).trows();
        }

        String sql = "UPDATE REGIONS SET REGION_NAME = ? WHERE REGION_ID = ?";
        Object[] param = new Object[]{region.getName(), region.getId()};

        jdbcTemplate.update(sql, param);
    }

    @Override
    public Collection<Region> findAll() {
        logger.debug("{} -> findAll()", REPOSITORY_NAME);

        String sql = "SELECT * FROM REGIONS";
        return jdbcTemplate.query(sql, new RegionRowMapper());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        logger.debug("{} ->  delete({}})", REPOSITORY_NAME, id);

        if (Objects.isNull(id)) {
            Error.of("Region").when(FIELD.MANDATORY).trows();
        }

        String sql = "DELETE FROM REGIONS WHERE REGION_ID = ?";

        jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsById(Integer id) {
        logger.debug("{} ->  exists({}})", REPOSITORY_NAME, id);

        if (Objects.isNull(id)) {
            Error.of("Region ID").when(FIELD.MANDATORY).trows();
        }
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM REGIONS WHERE REGION_ID = ?", new Object[]{id}, Integer.class) > 0;
    }

    @Override
    public Optional<Region> findByName(String name) {

        logger.debug("{} ->  findByName({}})", REPOSITORY_NAME, name);

        if (Objects.isNull(name)) {
            Error.of("Region Name").when(FIELD.MANDATORY).trows();
        }

        String sql = "SELECT * FROM REGIONS WHERE REGION_NAME = ?";

        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, new Object[]{name}, new RegionRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Region Name '{}' not found", name);
            return Optional.empty();
        }
    }
}

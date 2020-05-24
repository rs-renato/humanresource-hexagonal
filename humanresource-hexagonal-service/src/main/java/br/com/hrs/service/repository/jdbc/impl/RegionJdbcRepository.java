package br.com.hrs.service.repository.jdbc.impl;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.service.repository.jdbc.rowmapper.RegionRowMapper;
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
public class RegionJdbcRepository implements Repository<Region, Integer> {

    private JdbcTemplate jdbcTemplate;
    private final String REPOSITORY_NAME = getClass().getSimpleName();

    public RegionJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Region find(Integer id) {
        logger.debug("{} ->  find({}})", REPOSITORY_NAME, id);

        if (Objects.isNull(id)) {
            Error.of("Region ID").when(FIELD.MANDATORY).trows();
        }

        String sql = "SELECT * FROM REGIONS WHERE REGION_ID = ?";
        Object[] param = new Object[]{id};

        try {
            return jdbcTemplate.queryForObject(sql, param, new RegionRowMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Region Id '{}' not found", id);
            return null;
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
    public void delete(Integer id) {
        logger.debug("{} ->  delete({}})", REPOSITORY_NAME, id);

        if (Objects.isNull(id)) {
            Error.of("Region").when(FIELD.MANDATORY).trows();
        }

        String sql = "DELETE FROM REGIONS WHERE REGION_ID = ?";

        jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean exists(Integer id) {
        logger.debug("{} ->  exists({}})", REPOSITORY_NAME, id);

        if (Objects.isNull(id)) {
            Error.of("Region ID").when(FIELD.MANDATORY).trows();
        }
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM REGIONS WHERE REGION_ID = ?", new Object[]{id}, Integer.class) > 0;
    }
}

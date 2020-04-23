package br.com.hrs.service.repository.jdbc;

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
public class RegionRepositoryJdbc extends Repository<Region, Integer> {

    private JdbcTemplate jdbcTemplate;

    public RegionRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Region find(Integer regionId) {

        if (Objects.isNull(regionId)) {
            return null;
        }

        String sql = "SELECT * FROM REGIONS WHERE REGION_ID = ?";
        Object[] param = new Object[]{regionId};

        try {
            return jdbcTemplate.queryForObject(sql, param, new RegionRowMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Region Id '{}' not found", regionId);
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer save(Region region) {

        Integer savedId = null;

        if (Objects.nonNull(region)) {

            String sql = "INSERT INTO REGIONS (REGION_NAME) VALUES (?)";

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {

                PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setString(1, region.getName());

                return stmt;

            }, keyHolder);

            savedId = keyHolder.getKey().intValue();
            logger.debug("Returned saved ID {}", savedId);
        }

        return savedId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Region region) {

        if (Objects.nonNull(region)) {

            String sql = "UPDATE REGIONS SET REGION_NAME = ? WHERE REGION_ID = ?";
            Object[] param = new Object[]{region.getName(), region.getId()};

            return jdbcTemplate.update(sql, param) > 0;
        }

        return false;
    }

    @Override
    public Collection<Region> findAll() {
        String sql = "SELECT * FROM REGIONS";
        return jdbcTemplate.query(sql, new RegionRowMapper());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Integer regionId) {
        if (Objects.nonNull(regionId)) {

            String sql = "DELETE FROM REGIONS WHERE REGION_ID = ?";

            return jdbcTemplate.update(sql, regionId) > 0;
        }

        return false;
    }

    @Override
    public boolean exists(Integer regionId) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM REGIONS WHERE REGION_ID = ?", new Object[]{regionId}, Integer.class) > 0;
    }
}

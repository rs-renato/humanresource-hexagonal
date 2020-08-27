package br.com.hrs.persistence.repository.jdbc.impl;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.RegionRepository;
import br.com.hrs.core.repository.filter.Filter;
import br.com.hrs.core.repository.filter.QueryFilter;
import br.com.hrs.core.repository.pagination.Pagination;
import br.com.hrs.persistence.repository.jdbc.rowmapper.RegionRowMapper;
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
public class RegionJdbcRepository implements RegionRepository {

    private static Logger logger = LogManager.getLogger(RegionJdbcRepository.class);
    private JdbcTemplate jdbcTemplate;

    public RegionJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long count() {
        String sql = "SELECT COUNT(*) FROM REGIONS";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public Optional<Region> findById(Integer id) {
        logger.debug("find({}})", id);

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

        logger.debug("save({}})", region);

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

        logger.debug("update({}})", region);

        if (Objects.isNull(region)) {
            Error.of("Region").when(FIELD.MANDATORY).trows();
        }

        String sql = "UPDATE REGIONS SET REGION_NAME = ? WHERE REGION_ID = ?";
        Object[] param = new Object[]{region.getName(), region.getId()};

        jdbcTemplate.update(sql, param);
    }

    @Override
    public List<Region> findAll() {
        logger.debug("findAll()");

        String sql = "SELECT * FROM REGIONS";
        return jdbcTemplate.query(sql, new RegionRowMapper());
    }

    @Override
    public List<Region> findAll(Pagination pagination) {
        logger.debug("paginated findAll()");

        String sql = "SELECT * FROM REGIONS OFFSET ? ROWS FETCH FIRST ? ROWS ONLY";

        return jdbcTemplate.query(sql, new Object[]{pagination.getPage() * pagination.getSize(), pagination.getSize()}, new RegionRowMapper());
    }

    @Override
    public List<Region> findAll(Filter<Region> filter) {
        logger.debug("filtered findAll()");

        QueryFilter<Region> queryFilter = filter.filterToQuery();

        StringBuilder query = new StringBuilder("SELECT * FROM REGIONS")
                .append(" WHERE ")
                .append(queryFilter.getSql());

        return jdbcTemplate.query(query.toString(), queryFilter.getValues(), new RegionRowMapper());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        logger.debug("delete({}})", id);

        if (Objects.isNull(id)) {
            Error.of("Region").when(FIELD.MANDATORY).trows();
        }

        String sql = "DELETE FROM REGIONS WHERE REGION_ID = ?";

        jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsById(Integer id) {
        logger.debug("exists({}})", id);

        if (Objects.isNull(id)) {
            Error.of("Region ID").when(FIELD.MANDATORY).trows();
        }
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM REGIONS WHERE REGION_ID = ?", new Object[]{id}, Integer.class) > 0;
    }

    @Override
    public Optional<Region> findByName(String name) {

        logger.debug("findByName({}})", name);

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

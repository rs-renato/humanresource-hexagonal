package br.com.hrs.persistence.repository.jdbc.impl;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.repository.CountryRepository;
import br.com.hrs.core.repository.filter.Filter;
import br.com.hrs.core.repository.filter.QueryFilter;
import br.com.hrs.core.repository.pagination.Pagination;
import br.com.hrs.persistence.repository.jdbc.rowmapper.CountryRowMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import javax.management.Query;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Named
public class ContryJdbcRepository implements CountryRepository {

    private static Logger logger = LogManager.getLogger(ContryJdbcRepository.class);

    private JdbcTemplate jdbcTemplate;

    public ContryJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long count() {
        String sql = "SELECT COUNT(*) FROM COUNTRIES";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public Optional<Country> findById(String id) {

        logger.debug("find({}})", id);

        if (Objects.isNull(id)) {
            Error.of("Country ID").when(FIELD.MANDATORY).trows();
        }

        String sql = "SELECT * FROM COUNTRIES WHERE COUNTRY_ID = ?";
        Object[] param = new Object[]{id};

        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, param, new CountryRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Country Id '{}' not found", id);
            return Optional.empty();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Country save(Country country) {

        logger.debug("save({}})", country);

        if (Objects.isNull(country)) {
            Error.of("Country").when(FIELD.MANDATORY).trows();
        }

        String sql = "INSERT INTO COUNTRIES (COUNTRY_ID, COUNTRY_NAME, REGION_ID) VALUES (?,?,?)";

        Object[] param = new Object[]{country.getId(), country.getName(), country.getRegion().getId()};

        jdbcTemplate.update(sql, param);

        String savedId = country.getId();

        logger.debug("Returned saved ID {}", savedId);

        return country;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Country country) {

        logger.debug("update({}})", country);

        if (Objects.isNull(country)) {
            Error.of("Country").when(FIELD.MANDATORY).trows();
        }

        String sql = "UPDATE COUNTRIES SET COUNTRY_NAME = ?, REGION_ID = ? WHERE COUNTRY_ID = ?";
        Object[] param = new Object[]{country.getName(), country.getRegion().getId(), country.getId()};

        jdbcTemplate.update(sql, param);
    }

    @Override
    public List<Country> findAll() {
        logger.debug("findAll()");

        String sql = "SELECT * FROM COUNTRIES";
        return jdbcTemplate.query(sql, new CountryRowMapper());
    }

    @Override
    public List<Country> findAll(Pagination pagination) {
        logger.debug("paginated findAll()");

        String sql = "SELECT * FROM COUNTRIES OFFSET ? ROWS FETCH FIRST ? ROWS ONLY";

        return jdbcTemplate.query(sql, new Object[]{pagination.getPage() * pagination.getSize(), pagination.getSize()}, new CountryRowMapper());
    }

    @Override
    public List<Country> findAll(Filter<Country> filter) {
        logger.debug("filtered findAll()");

        String sql = "SELECT * FROM COUNTRIES OFFSET ? ROWS FETCH FIRST ? ROWS ONLY";

        QueryFilter<Country> queryFilter = filter.filterToQuery();

        StringBuilder query = new StringBuilder("SELECT * FROM COUNTRIES")
                .append(" WHERE ")
                .append(queryFilter.getSql());

        return jdbcTemplate.query(sql, queryFilter.getValues(), new CountryRowMapper());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        logger.debug("delete({}})", id);

        if (Objects.isNull(id)) {
            Error.of("Country ID").when(FIELD.MANDATORY).trows();
        }

        String sql = "DELETE FROM COUNTRIES WHERE COUNTRY_ID = ?";

        jdbcTemplate.update(sql, id);

    }

    @Override
    public boolean existsById(String id) {

        logger.debug(" exists({}})", id);

        if (Objects.isNull(id)) {
            Error.of("Country ID").when(FIELD.MANDATORY).trows();
        }

        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM COUNTRIES WHERE COUNTRY_ID = ?", new Object[]{id}, Integer.class) > 0;
    }

    @Override
    public Optional<List<Country>> findByRegionId(Integer regionId) {

        logger.debug("findByRegionId({}})", regionId);

        if (Objects.isNull(regionId)) {
            Error.of("Region ID").when(FIELD.MANDATORY).trows();
        }

        String sql = "SELECT * FROM COUNTRIES WHERE REGION_ID = ?";
        return Optional.of(jdbcTemplate.query(sql, new Object[]{regionId}, new CountryRowMapper()));
    }
}
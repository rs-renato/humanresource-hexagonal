package br.com.hrs.service.repository.jdbc.impl;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.service.repository.jdbc.rowmapper.CountryRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.Collection;
import java.util.Objects;

@Named
public class ContryJdbcRepository implements Repository<Country, String> {

    private JdbcTemplate jdbcTemplate;
    private final String REPOSITORY_NAME = getClass().getSimpleName();

    public ContryJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Country find(String id) {

        logger.debug("{} ->  find({}})", REPOSITORY_NAME, id);

        if (Objects.isNull(id)) {
            Error.of("Country ID").when(FIELD.MANDATORY).trows();
        }

        String sql = "SELECT * FROM COUNTRIES WHERE COUNTRY_ID = ?";
        Object[] param = new Object[]{id};

        try {
            return jdbcTemplate.queryForObject(sql, param, new CountryRowMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Country Id '{}' not found", id);
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Country save(Country country) {

        logger.debug("{} ->  save({}})", REPOSITORY_NAME, country);

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

        logger.debug("{} ->  update({}})", REPOSITORY_NAME, country);

        if (Objects.isNull(country)) {
            Error.of("Country").when(FIELD.MANDATORY).trows();
        }

        String sql = "UPDATE COUNTRIES SET COUNTRY_NAME = ?, REGION_ID = ? WHERE COUNTRY_ID = ?";
        Object[] param = new Object[]{country.getName(), country.getRegion().getId(), country.getId()};

        jdbcTemplate.update(sql, param);
    }

    @Override
    public Collection<Country> findAll() {
        logger.debug("{} -> findAll()", REPOSITORY_NAME);

        String sql = "SELECT * FROM COUNTRIES";
        return jdbcTemplate.query(sql, new CountryRowMapper());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        logger.debug("{} ->  delete({}})", REPOSITORY_NAME, id);

        if (Objects.isNull(id)) {
            Error.of("Country ID").when(FIELD.MANDATORY).trows();
        }

        String sql = "DELETE FROM COUNTRIES WHERE COUNTRY_ID = ?";

        jdbcTemplate.update(sql, id);

    }

    @Override
    public boolean exists(String id) {

        logger.debug("{} ->  exists({}})", REPOSITORY_NAME, id);

        if (Objects.isNull(id)) {
            Error.of("Country ID").when(FIELD.MANDATORY).trows();
        }

        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM COUNTRIES WHERE COUNTRY_ID = ?", new Object[]{id}, Integer.class) > 0;
    }
}

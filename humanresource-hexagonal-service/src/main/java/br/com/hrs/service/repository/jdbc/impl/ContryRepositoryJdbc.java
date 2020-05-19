package br.com.hrs.service.repository.jdbc.impl;

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
public class ContryRepositoryJdbc implements Repository<Country, String> {

    private JdbcTemplate jdbcTemplate;

    public ContryRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Country find(String countryId) {

        if (Objects.isNull(countryId)) {
            return null;
        }

        String sql = "SELECT * FROM COUNTRIES WHERE COUNTRY_ID = ?";
        Object[] param = new Object[]{countryId};

        try {
            return jdbcTemplate.queryForObject(sql, param, new CountryRowMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Country Id '{}' not found", countryId);
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Country save(Country country) {

        String savedId = null;

        if (Objects.nonNull(country)) {

            String sql = "INSERT INTO COUNTRIES (COUNTRY_ID, COUNTRY_NAME, REGION_ID) VALUES (?,?,?)";

            Object[] param = new Object[]{country.getId(), country.getName(), country.getRegion().getId()};

            jdbcTemplate.update(sql, param);

            savedId = country.getId();
            logger.debug("Returned saved ID {}", savedId);
        }

        return country;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Country country) {

        if (Objects.nonNull(country)) {

            String sql = "UPDATE COUNTRIES SET COUNTRY_NAME = ?, REGION_ID = ? WHERE COUNTRY_ID = ?";
            Object[] param = new Object[]{country.getName(), country.getRegion().getId(), country.getId()};

            jdbcTemplate.update(sql, param);
        }
    }

    @Override
    public Collection<Country> findAll() {
        String sql = "SELECT * FROM COUNTRIES";
        return jdbcTemplate.query(sql, new CountryRowMapper());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(String countryId) {
        if (Objects.nonNull(countryId)) {

            String sql = "DELETE FROM COUNTRIES WHERE COUNTRY_ID = ?";

            return jdbcTemplate.update(sql, countryId) > 0;
        }

        return false;
    }

    @Override
    public boolean exists(String countryId) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM COUNTRIES WHERE COUNTRY_ID = ?", new Object[]{countryId}, Integer.class) > 0;
    }
}

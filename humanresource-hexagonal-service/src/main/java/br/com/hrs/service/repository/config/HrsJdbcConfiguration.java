package br.com.hrs.service.repository.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@Import(HrsDataBaseConfiguration.class)
@ComponentScan("br.com.hrs.service.repository.jdbc.impl")
public class HrsJdbcConfiguration {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource hrsDataSource) {
        return new JdbcTemplate(hrsDataSource);
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource hrsDataSource) {
        return new DataSourceTransactionManager(hrsDataSource);
    }
}
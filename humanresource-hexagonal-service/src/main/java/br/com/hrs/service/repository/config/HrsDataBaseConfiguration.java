package br.com.hrs.service.repository.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class HrsDataBaseConfiguration {

    @Bean
    public DataSource hrsDataSource() {
        return new EmbeddedDatabaseBuilder()
                .addScripts("create.sql", "insert.sql")
                .setType(EmbeddedDatabaseType.DERBY)
                .build();
    }
}

package br.com.hrs.service.repository.jdbc;

import br.com.hrs.core.repository.JobRepository;
import br.com.hrs.service.repository.JobRepositoryTest;
import br.com.hrs.service.repository.config.HrsJdbcConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

@DisplayName("Jdbc Repository - Job")
@ContextConfiguration(classes = HrsJdbcConfiguration.class)
@ExtendWith(SpringExtension.class)
public class JobJdbcRepositoryTest extends JobRepositoryTest {

    @Inject
    private JobRepository jobJdbcRepository;

    @Override
    protected JobRepository getJobRepository() {
        return jobJdbcRepository;
    }
}
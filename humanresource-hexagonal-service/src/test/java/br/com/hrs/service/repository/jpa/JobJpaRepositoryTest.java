package br.com.hrs.service.repository.jpa;

import br.com.hrs.core.repository.JobRepository;
import br.com.hrs.service.repository.JobRepositoryTest;
import br.com.hrs.service.repository.config.HrsJpaConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

@DisplayName("Jpa Repository - Job")
@ContextConfiguration(classes = HrsJpaConfiguration.class)
@ExtendWith(SpringExtension.class)
public class JobJpaRepositoryTest extends JobRepositoryTest {

    @Inject
    private JobRepository jobJpaRepository;

    @Override
    protected JobRepository getJobRepository() {
        return jobJpaRepository;
    }
}
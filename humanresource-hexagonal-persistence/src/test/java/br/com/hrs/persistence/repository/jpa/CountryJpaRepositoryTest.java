package br.com.hrs.persistence.repository.jpa;

import br.com.hrs.core.repository.CountryRepository;
import br.com.hrs.persistence.config.HrsJpaConfiguration;
import br.com.hrs.persistence.repository.CountryRepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

@DisplayName("Jpa Repository - Country")
@ContextConfiguration(classes = HrsJpaConfiguration.class)
@ExtendWith(SpringExtension.class)
public class CountryJpaRepositoryTest extends CountryRepositoryTest {

    @Inject
    private CountryRepository countryJpaRepository;

    @Override
    public CountryRepository getCountryRepository() {
        return this.countryJpaRepository;
    }
}
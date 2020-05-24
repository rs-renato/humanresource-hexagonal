package br.com.hrs.service.repository.jdbc;

import br.com.hrs.core.repository.LocationRepository;
import br.com.hrs.service.repository.LocationRepositoryTest;
import br.com.hrs.service.repository.config.HrsJdbcConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

@DisplayName("Jdbc Repository - Location")
@ContextConfiguration(classes = HrsJdbcConfiguration.class)
@ExtendWith(SpringExtension.class)
public class LocationJdbcRepositoryTest extends LocationRepositoryTest {

    @Inject
    private LocationRepository locationJdbcRepository;

    @Override
    protected LocationRepository getLocationRepository() {
        return locationJdbcRepository;
    }
}
package br.com.hrs.service.repository.jdbc;

import br.com.hrs.core.repository.RegionRepository;
import br.com.hrs.service.repository.RegionRepositoryTest;
import br.com.hrs.service.repository.config.HrsJdbcConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

@DisplayName("Jdbc Repository - Region")
@ContextConfiguration(classes = HrsJdbcConfiguration.class)
@ExtendWith(SpringExtension.class)
public class RegionJdbcRepositoryTest extends RegionRepositoryTest {

    @Inject
    private RegionRepository regionJdbcRepository;

    @Override
    protected RegionRepository getRegionRepository() {
        return regionJdbcRepository;
    }
}
package br.com.hrs.persistence.repository.jpa;

import br.com.hrs.core.repository.RegionRepository;
import br.com.hrs.persistence.config.HrsJpaConfiguration;
import br.com.hrs.persistence.repository.RegionRepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

@DisplayName("Jpa Repository - Region")
@ContextConfiguration(classes = HrsJpaConfiguration.class)
@ExtendWith(SpringExtension.class)
public class RegionJpaRepositoryTest extends RegionRepositoryTest {

    @Inject
    private RegionRepository regionJpaRepository;

    @Override
    protected RegionRepository getRegionRepository() {
        return regionJpaRepository;
    }
}
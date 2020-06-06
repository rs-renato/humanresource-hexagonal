package br.com.hrs.persistence.repository.jpa;

import br.com.hrs.core.repository.DepartmentRepository;
import br.com.hrs.persistence.config.HrsJpaConfiguration;
import br.com.hrs.persistence.repository.DepartmentRepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

@DisplayName("Jpa Repository - Department")
@ContextConfiguration(classes = HrsJpaConfiguration.class)
@ExtendWith(SpringExtension.class)
public class DepartmentJpaRepositoryTest extends DepartmentRepositoryTest {

    @Inject
    private DepartmentRepository departmentJpaRepository;

    @Override
    protected DepartmentRepository getDepartmentRepository() {
        return departmentJpaRepository;
    }
}
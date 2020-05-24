package br.com.hrs.service.repository.jdbc;

import br.com.hrs.core.repository.DepartmentRepository;
import br.com.hrs.service.repository.DepartmentRepositoryTest;
import br.com.hrs.service.repository.config.HrsJdbcConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

@DisplayName("Jdbc Repository - Department")
@ContextConfiguration(classes = HrsJdbcConfiguration.class)
@ExtendWith(SpringExtension.class)
public class DepartmentJdbcRepositoryTest extends DepartmentRepositoryTest {

    @Inject
    private DepartmentRepository departmentJdbcRepository;

    @Override
    protected DepartmentRepository getDepartmentRepository() {
        return departmentJdbcRepository;
    }
}
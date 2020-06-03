package br.com.hrs.service.repository.jdbc;

import br.com.hrs.core.repository.EmployeeRepository;
import br.com.hrs.service.repository.EmployeeRepositoryTest;
import br.com.hrs.service.repository.config.HrsJdbcConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

@DisplayName("Jdbc Repository - Employee")
@ContextConfiguration(classes = HrsJdbcConfiguration.class)
@ExtendWith(SpringExtension.class)
public class EmployeeJdbcRepositoryTest extends EmployeeRepositoryTest {

    @Inject
    private EmployeeRepository employeeJdbcRepository;

    @Override
    public EmployeeRepository getEmployeeRepository() {
        return this.employeeJdbcRepository;
    }
}
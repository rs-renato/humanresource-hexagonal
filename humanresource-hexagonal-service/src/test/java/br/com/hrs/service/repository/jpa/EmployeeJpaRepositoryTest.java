package br.com.hrs.service.repository.jpa;

import br.com.hrs.core.repository.EmployeeRepository;
import br.com.hrs.service.repository.EmployeeRepositoryTest;
import br.com.hrs.service.repository.config.HrsJpaConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

@DisplayName("Jpa Repository - Employee")
@ContextConfiguration(classes = HrsJpaConfiguration.class)
@ExtendWith(SpringExtension.class)
public class EmployeeJpaRepositoryTest extends EmployeeRepositoryTest {

    @Inject
    private EmployeeRepository employeeJpaRepository;

    @Override
    public EmployeeRepository getEmployeeRepository() {
        return this.employeeJpaRepository;
    }
}
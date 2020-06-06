package br.com.hrs.persistence.repository.jpa;

import br.com.hrs.core.repository.EmployeeRepository;
import br.com.hrs.persistence.config.HrsJpaConfiguration;
import br.com.hrs.persistence.repository.EmployeeRepositoryTest;
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
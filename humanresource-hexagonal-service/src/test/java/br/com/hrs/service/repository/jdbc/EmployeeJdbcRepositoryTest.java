package br.com.hrs.service.repository.jdbc;

import br.com.hrs.core.exception.HrsMandatoryException;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.service.repository.config.HrsJdbcConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Date;

@DisplayName("Jdbc  Repository - Employee")
@ContextConfiguration(classes = HrsJdbcConfiguration.class)
@ExtendWith(SpringExtension.class)
public class EmployeeJdbcRepositoryTest {

    private Integer EMPLOYEE_ID = 10;

    Logger logger = LogManager.getLogger(EmployeeJdbcRepositoryTest.class);

    @Inject
    private Repository<Employee, Integer> employeeJdbcRepository;

    @Test
    @DisplayName("Finds for null Employee")
    public void test01() {
        RuntimeException exception = Assertions.assertThrows(HrsMandatoryException.class, () -> {
            employeeJdbcRepository.find(null);
        });

        Assertions.assertTrue(exception.getMessage().matches("Employee.*mandatory"), String.format("Employee ID mandatory message wrong (%s)", exception.getMessage()));
    }

    @Test
    @DisplayName("Finds for inexistent Employee")
    public void test02() {
        Employee employee = employeeJdbcRepository.find(-1);
        logger.info(employee);
        Assertions.assertNull(employee, "Employee should be null");
    }

    @Test
    @DisplayName("Updates Employee")
    public void test03() {

        Employee employee = employeeJdbcRepository.find(EMPLOYEE_ID);
        logger.info(employee);
        employee.setFirstName(employee.getFirstName() + " altered");
        employee.setManager(new Employee.Builder().id(1).build());
        employeeJdbcRepository.update(employee);

        employee = employeeJdbcRepository.find(EMPLOYEE_ID);

        Assertions.assertTrue(employee.getFirstName().contains("altered"), "Employee should be altered");
    }

    @Test
    @DisplayName("Saves Employee")
    public void test04() {

        Employee employee = new Employee.Builder()
                .firstName("renato")
                .lastName("rodrigues")
                .email("renato.rsilva")
                .phone("515.123.4567")
                .hireDate(new Date())
                .job(new Job.Builder().id("AD_PRES").build())
                .salary(24000f)
                .commissionPercent(.1f)
                .manager(new Employee.Builder().id(1).build())
                .department(new Department.Builder().id(1).build())
                .build();

        Employee employeeSaved= employeeJdbcRepository.save(employee);
        Assertions.assertNotNull(employeeSaved, "Employee saved should not be null");
        Employee employeeFound = employeeJdbcRepository.find(employeeSaved.getId());
        Assertions.assertEquals(employeeSaved, employeeFound, String.format("Employee should be equals"));
    }

    @Test
    @DisplayName("Finds all Employees")
    public void test05() {
        Collection<Employee> employees = employeeJdbcRepository.findAll();
        logger.info(employees);
        Assertions.assertNotNull(employees, "Employees should be listed");
        Assertions.assertTrue(employees.size() >= 107, "Employees should be listed at all");
    }

    @Test
    @DisplayName("Finds for Employee")
    public void test06() {
        Employee employee = employeeJdbcRepository.find(EMPLOYEE_ID);
        logger.info(employee);
        Assertions.assertNotNull(employee, "Employee should not be null");
    }

    @Test
    @DisplayName("Employee exists")
    public void test07() {
        boolean exists = employeeJdbcRepository.exists(EMPLOYEE_ID);
        Assertions.assertTrue(exists, "Employee should be existent");
    }

    @Test
    @DisplayName("Employee doesn't exists")
    public void test08() {
        boolean exists = employeeJdbcRepository.exists(-1);
        Assertions.assertFalse(exists, "Employee should not be existent");
    }

    @Test
    @DisplayName("Deletes Employee")
    public void test09() {
        employeeJdbcRepository.delete(EMPLOYEE_ID);
        Employee employee = employeeJdbcRepository.find(EMPLOYEE_ID);
        Assertions.assertNull(employee, "Employee still existing");
    }
}
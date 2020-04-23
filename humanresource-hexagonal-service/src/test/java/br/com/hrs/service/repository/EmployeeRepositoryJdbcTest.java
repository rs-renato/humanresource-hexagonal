package br.com.hrs.service.repository;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.EmployeeRepository;
import br.com.hrs.service.DatabaseConfiguration;
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

@DisplayName("Repository - Employee")
@ContextConfiguration(classes = DatabaseConfiguration.class)
@ExtendWith(SpringExtension.class)
public class EmployeeRepositoryJdbcTest {

    private Integer EMPLOYEE_ID = 10;

    Logger logger = LogManager.getLogger(EmployeeRepositoryJdbcTest.class);

    @Inject
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("Finds for null Employee")
    public void test01() {
        Employee employee = employeeRepository.find(null);
        Assertions.assertNull(employee, "Employee should be null");
    }

    @Test
    @DisplayName("Finds for inexistent Employee")
    public void test02() {
        Employee employee = employeeRepository.find(-1);
        logger.info(employee);
        Assertions.assertNull(employee, "Employee should be null");
    }

    @Test
    @DisplayName("Updates Employee")
    public void test03() {

        Employee employee = employeeRepository.find(EMPLOYEE_ID);
        logger.info(employee);
        employee.setFirstName(employee.getFirstName() + " altered");
        employee.setManager(new Employee.Builder().id(1).build());
        boolean updated = employeeRepository.update(employee);

        employee = employeeRepository.find(EMPLOYEE_ID);

        Assertions.assertTrue(updated, "Employee should be updated");
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

        Integer savedId = employeeRepository.save(employee);

        employee = employeeRepository.find(savedId);

        Assertions.assertNotNull(savedId, "Employee should be saved and return");
        Assertions.assertNotNull(employee, "Employee should be saved");
        Assertions.assertTrue(savedId.equals(employee.getId()), "Employee saved needs to match saved id");
    }

    @Test
    @DisplayName("List Employees")
    public void test05() {
        Collection<Employee> employees = employeeRepository.findAll();
        logger.info(employees);
        Assertions.assertNotNull(employees, "Employees should be listed");
        Assertions.assertTrue(employees.size() >= 27, "Employees should be listed at all");
    }

    @Test
    @DisplayName("Finds for Employee")
    public void test06() {
        Employee employee = employeeRepository.find(EMPLOYEE_ID);
        logger.info(employee);
        Assertions.assertNotNull(employee, "Employee should not be null");
    }

    @Test
    @DisplayName("Deletes Employee")
    public void test07() {
        boolean deleted = employeeRepository.delete(EMPLOYEE_ID);
        Employee employee = employeeRepository.find(EMPLOYEE_ID);
        Assertions.assertTrue(deleted, "Employee should be deleted");
        Assertions.assertNull(employee, "Employee still existing");
    }

    @Test
    @DisplayName("Deletes inexistend Employee")
    public void test08() {
        boolean deleted = employeeRepository.delete(-1);
        Assertions.assertFalse(deleted, "Employee should not be deleted");
    }
}
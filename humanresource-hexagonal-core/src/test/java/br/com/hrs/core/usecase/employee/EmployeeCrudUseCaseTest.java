package br.com.hrs.core.usecase.employee;

import br.com.hrs.core.HrsBuildConfiguration;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Optional;

@ContextConfiguration(classes = HrsBuildConfiguration.class)
@ExtendWith(SpringExtension.class)
@DisplayName("Employee Crud Use Case")
public class EmployeeCrudUseCaseTest {

    @Inject
    private EmployeeUseCase employeeCrudUseCase;

    private static Employee employee;

    @BeforeAll
    public static void setUp() throws ParseException {
        employee = new Employee.Builder()
                .id(100)
                .firstName("Steven")
                .lastName("King")
                .email("SKING")
                .phone("515.123.4567")
                .hireDate(new SimpleDateFormat("dd.MM.yyyy").parse("17.06.2003"))
                .job(new Job.Builder().id("AD_VP").build())
                .salary(24000f)
                .commissionPercent(.1f)
                .manager(new Employee.Builder().id(100).build())
                .department(new Department.Builder().id(2).build())
                .build();
    }

    @Test
    @DisplayName("Exists an Employees")
    public void test01() {
        boolean exists = employeeCrudUseCase.existsById(employee.getId());
        Assertions.assertTrue(exists, String.format("Employee should exist"));
    }

    @Test
    @DisplayName("Saves an Employee")
    public void test02() {
        Employee employeeSaved = employeeCrudUseCase.save(employee);
        Assertions.assertNotNull(employeeSaved, String.format("Employee saved should not be null"));
        Optional<Employee> employeeOpt = employeeCrudUseCase.findById(employeeSaved.getId());
        Assertions.assertEquals(employeeSaved, employeeOpt.get(), String.format("Employee should be equals"));
    }

    @Test
    @DisplayName("Finds an Employee")
    public void test03() {
        Optional<Employee> employeeOpt = employeeCrudUseCase.findById(employee.getId());
        Assertions.assertTrue(employeeOpt.isPresent(), String.format("Employee should not be null"));
    }

    @Test
    @DisplayName("Finds all Employees")
    public void test04() {
        Collection<Employee> employees = employeeCrudUseCase.findAll();
        Assertions.assertNotNull(employees, String.format("Employees should not be null"));
        Assertions.assertEquals(2, employees.size(), String.format("Employees size doesn't match"));
    }

    @Test
    @DisplayName("Updates an Employee")
    public void test05() {

        employee.setLastName(employee.getLastName() + "updated");
        employee.setFirstName(employee.getFirstName() + " updated");

        Optional<Employee> employeeOpt = employeeCrudUseCase.findById(employee.getId());

        Assertions.assertTrue(employeeOpt.isPresent(), String.format("Employee should not be null"));
        Assertions.assertEquals(employeeOpt.get().getFirstName(), employee.getFirstName(), String.format("Employee firstname should be updated"));
        Assertions.assertEquals(employeeOpt.get().getLastName(), employee.getLastName(), String.format("Employee lastname should be updated"));
    }

    @Test
    @DisplayName("Deletes an Employee")
    public void test06() {

        employeeCrudUseCase.deleteById(employee.getId());

        Optional<Employee> employeeOpt = employeeCrudUseCase.findById(employee.getId());

        Assertions.assertFalse(employeeOpt.isPresent(), String.format("Employee should be null"));
    }
}

package br.com.hrs.core.business.service;

import br.com.hrs.core.DevEnvironmentConfiguration;
import br.com.hrs.core.exception.HrsMandatoryException;
import br.com.hrs.core.exception.HrsNotFoundException;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

@ContextConfiguration(classes = DevEnvironmentConfiguration.class)
@ExtendWith(SpringExtension.class)
@DisplayName("Adapter Tests - Promote Employee")
public class EmployeeServiceTest {

    @Inject
    private EmployeeService employeeService;

    private Integer employeeId = 1;
    private String jobId = "AN";
    private Integer departmentId = 1;

    @Test
    @DisplayName("Return null employee when passed null")
    public void test01() {

        Employee employee = employeeService.get(null);
        Assertions.assertNull(employee, "Employee shoul be null");
    }

    @Test
    @DisplayName("Promotes with null employee id but its mandatory")
    public void test02() {

        RuntimeException exception = Assertions.assertThrows(HrsMandatoryException.class, () -> {
            employeeService.promote(null, jobId, departmentId);
        });

        Assertions.assertTrue(exception.getMessage().matches("Employee.*mandatory"), String.format("Employee ID mandatory message wrong (%s)", exception.getMessage()));
    }

    @Test
    @DisplayName("Promotes with null job id but its mandatory")
    public void test03() {

        RuntimeException exception = Assertions.assertThrows(HrsMandatoryException.class, () -> {
            employeeService.promote(employeeId, null, departmentId);
        });

        Assertions.assertTrue(exception.getMessage().matches("Job.*mandatory"), String.format("Job ID mandatory message wrong (%s)", exception.getMessage()));
    }

    @Test
    @DisplayName("Promotes with null department id but its mandatory")
    public void test04() {

        RuntimeException exception = Assertions.assertThrows(HrsMandatoryException.class, () -> {
            employeeService.promote(employeeId, jobId, null);
        });

        Assertions.assertTrue(exception.getMessage().matches("Department.*mandatory"), String.format("Department ID mandatory message wrong (%s)", exception.getMessage()));
    }

    @Test
    @DisplayName("Promotes with employee id but its not found")
    public void test05() {

        RuntimeException exception = Assertions.assertThrows(HrsNotFoundException.class, () -> {
            employeeService.promote(-1, jobId, departmentId);
        });

        Assertions.assertTrue(exception.getMessage().matches("Employee.*not found"), String.format("Employee ID not found message wrong (%s)", exception.getMessage()));
    }

    @Test
    @DisplayName("Promotes with job id but its not found")
    public void test06() {

        RuntimeException exception = Assertions.assertThrows(HrsNotFoundException.class, () -> {
            employeeService.promote(employeeId, "NONE", departmentId);
        });

        Assertions.assertTrue(exception.getMessage().matches("Job.*not found"), String.format("Job ID not found message wrong (%s)", exception.getMessage()));
    }

    @Test
    @DisplayName("Promotes with department id but its not found")
    public void test07() {

        RuntimeException exception = Assertions.assertThrows(HrsNotFoundException.class, () -> {
            employeeService.promote(employeeId, jobId, -1);
        });

        Assertions.assertTrue(exception.getMessage().matches("Department.*not found"), String.format("Department ID not found message wrong (%s)", exception.getMessage()));
    }

    @Test
    @DisplayName("Should promote employee ")
    public void test08() {

        employeeService.promote(employeeId, jobId, departmentId);

        Employee employee = employeeService.get(employeeId);

        Assertions.assertEquals(employeeId, employee.getId());
        Assertions.assertEquals(jobId, employee.getJob().getId());
        Assertions.assertEquals(departmentId, employee.getDepartment().getId());
        Assertions.assertEquals(Employee.STATUS.PROMOTED, employee.getStatus());
    }
}

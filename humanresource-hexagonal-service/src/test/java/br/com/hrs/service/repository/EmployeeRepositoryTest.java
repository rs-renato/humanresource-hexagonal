package br.com.hrs.service.repository;

import br.com.hrs.core.exception.HrsMandatoryException;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import br.com.hrs.core.repository.EmployeeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public abstract class EmployeeRepositoryTest {

    private static final Integer EMPLOYEE_ID = 2;
    private static final Integer DEPARTMENT_ID = 3;
    private static Integer NEW_EMPLOYEE_ID;

    Logger logger = LogManager.getLogger(EmployeeRepositoryTest.class);

    protected abstract EmployeeRepository getEmployeeRepository();

    @Test
    @DisplayName("Finds for null Employee")
    public void test01() {
        RuntimeException exception = Assertions.assertThrows(HrsMandatoryException.class, () -> {
            getEmployeeRepository().findById(null);
        });

        Assertions.assertTrue(exception.getMessage().matches("Employee.*mandatory"), String.format("Employee ID mandatory message wrong (%s)", exception.getMessage()));
    }

    @Test
    @DisplayName("Finds for inexistent Employee")
    public void test02() {
        Optional<Employee> employeeOpt = getEmployeeRepository().findById(-1);
        Assertions.assertFalse(employeeOpt.isPresent(), "Employee should be null");
    }

    @Test
    @DisplayName("Updates Employee")
    public void test03() {
        Optional<Employee> employeeOpt = getEmployeeRepository().findById(EMPLOYEE_ID);

        employeeOpt.get().setFirstName(employeeOpt.get().getFirstName() + " altered");
        employeeOpt.get().setDepartment(new Department.Builder().id(DEPARTMENT_ID).build());

        getEmployeeRepository().update(employeeOpt.get());

        Optional<Employee> employeeOptSaved  = getEmployeeRepository().findById(EMPLOYEE_ID);

        Assertions.assertEquals(employeeOptSaved.get().getFirstName(), employeeOpt.get().getFirstName(), "Employee firstname should be altered");
        Assertions.assertEquals(employeeOptSaved.get().getDepartment().getId(), employeeOpt.get().getDepartment().getId(), "Employee department id should be altered");
    }

    @Test
    @DisplayName("Saves Employee")
    public void test04() {
        Employee employee =  new Employee.Builder()
                .firstName("Renato")
                .lastName("Silva")
                .email("renato@email.com")
                .phone("515.123.4567")
                .hireDate(new Date())
                .job(new Job.Builder().id("AD_PRES").build())
                .salary(24000f)
                .commissionPercent(.1f)
                .manager(new Employee.Builder().id(EMPLOYEE_ID).build())
                .department(new Department.Builder().id(DEPARTMENT_ID).build())
                .build();

        Employee employeeSaved = getEmployeeRepository().save(employee);

        Optional<Employee> employeeOpt = getEmployeeRepository().findById(employee.getId());

        Assertions.assertTrue(employeeOpt.isPresent(), "Employee should be saved");
        Assertions.assertEquals(employeeSaved, employeeOpt.get(), "Employee should be equals");
        Assertions.assertEquals("Renato", employeeOpt.get().getFirstName(), "Employee firstname should be saved");

        NEW_EMPLOYEE_ID = employeeSaved.getId();
    }

    @Test
    @DisplayName("Finds all Employees")
    public void test05() {
        Collection<Employee> employees = getEmployeeRepository().findAll();
        logger.info(employees);
        Assertions.assertNotNull(employees, "Employees should be listed");
        Assertions.assertTrue(employees.size() >= 107, "Employees should be listed at all");
    }

    @Test
    @DisplayName("Finds for Employee")
    public void test06() {
        Optional<Employee> employeeOpt = getEmployeeRepository().findById(EMPLOYEE_ID);
        Assertions.assertTrue(employeeOpt.isPresent(), "Employee should not be null");
    }

    @Test
    @DisplayName("Deletes Employee")
    public void test07() {
        Optional<Employee> employee = getEmployeeRepository().findById(NEW_EMPLOYEE_ID);
        getEmployeeRepository().deleteById(employee.get().getId());
        boolean exists = getEmployeeRepository().existsById(employee.get().getId());
        Assertions.assertFalse(exists, "Employee still existing");
    }

    @Test
    @DisplayName("Employee exists")
    public void test08() {
        boolean exists = getEmployeeRepository().existsById(EMPLOYEE_ID);
        Assertions.assertTrue(exists, "Employee should be existent");
    }

    @Test
    @DisplayName("Employee doesn't exists")
    public void test09() {
        boolean exists = getEmployeeRepository().existsById(-1);
        Assertions.assertFalse(exists, "Employee should not be existent");
    }
}
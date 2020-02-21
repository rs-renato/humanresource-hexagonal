package br.com.hrs.core.business.service;

import br.com.hrs.core.exception.HrsMandatoryException;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Employee Promotion Business Service Tests")
public class EmployeePromotionBusinessServiceTest {

    private Logger logger = LogManager.getLogger(EmployeePromotionService.class);

    private static Employee employee;
    private static Job job;
    private static Department department;

    private static EmployeePromotionService service;

    @BeforeAll
    static void setUp(){
        service = new EmployeePromotionService();
        job = new Job("AR","Architect", 3_000f, 5_000f);
        department = new Department(1, "IT", null);
        employee = new Employee(1,"renato@gmail",1000f, null, null);
    }

    @Test
    @DisplayName("Employee set as null but its mandatory")
    void test01(){
        RuntimeException exception =  Assertions.assertThrows(HrsMandatoryException.class,()-> {
            service.promote(null, job, department);
        });

        Assertions.assertTrue(exception.getMessage().contains("Employee"), String.format("Employee mandatory message wrong (%s)", exception.getMessage()));
    }

    @Test
    @DisplayName("Job set as null but its mandatory")
    void test02(){
        RuntimeException exception =  Assertions.assertThrows(HrsMandatoryException.class,()-> {
            service.promote(employee, null, department);
        });

        Assertions.assertTrue(exception.getMessage().contains("Job"), String.format("Job mandatory message wrong (%s)", exception.getMessage()));
    }

    @Test
    @DisplayName("Department set as null but its mandatory")
    void test03(){
        RuntimeException exception = Assertions.assertThrows(HrsMandatoryException.class,()-> {
            service.promote(employee, job, null);
        });

        Assertions.assertTrue(exception.getMessage().contains("Department"), String.format("Department mandatory message wrong (%s)", exception.getMessage()));
    }

    @Test
    @DisplayName("Promote Employee")
    void test04() {
        service.promote(employee, job, department);
        Assertions.assertEquals(employee.getJob(), job, "Job not equal for promoted Employee");
        Assertions.assertEquals(employee.getDepartment(), department, "Department not equal for promoted Employee");
        Assertions.assertEquals(employee.getSalary(), job.getMinSalary(), "Salary not equal for promoted Employee");
    }
}
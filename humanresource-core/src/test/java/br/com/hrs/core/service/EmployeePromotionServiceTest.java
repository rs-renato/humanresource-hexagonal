package br.com.hrs.core.service;

import br.com.hrs.core.error.HrsMandatoryException;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Job;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

@DisplayName("Employee Promotion Service Tests")
public class EmployeePromotionServiceTest {

    private static Employee employee;
    private static Job job;
    private static Department department;

    private static EmployeePromotionService service;

    @BeforeAll
    static void setUp(){
        service = new EmployeePromotionService();
        job = new Job("AR","Architect", 3_000f, 5_000f);
        department = new Department(1, "IT", null, null);
        employee = new Employee(1,"renato","silva","renato@gmail","1234467890",new Date(),1000f,0.5f, null, null, null);
    }

    @Test
    @DisplayName("Employee set as null but its mandatory")
    void test01(){
        RuntimeException exception =  Assertions.assertThrows(HrsMandatoryException.class,()-> {
            service.promote(null, job, department);
        });

        Assertions.assertTrue(exception.getMessage().contains("Employee"),"Employee mandatory message wrong");

    }

    @Test
    @DisplayName("Job set as null but its mandatory")
    void test02(){
        RuntimeException exception =  Assertions.assertThrows(HrsMandatoryException.class,()-> {
            service.promote(employee, null, department);
        });

        Assertions.assertTrue(exception.getMessage().contains("Job"),"Job mandatory message wrong");
    }

    @Test
    @DisplayName("Department set as null but its mandatory")
    void test03(){
        RuntimeException exception = Assertions.assertThrows(HrsMandatoryException.class,()-> {
            service.promote(employee, job, null);
        });

        Assertions.assertTrue(exception.getMessage().contains("Department"),"Department mandatory message wrong");
    }

    @Test
    @DisplayName("Promote Employee")
    void test04() {
        service.promote(employee, job, department);
        Assertions.assertEquals(employee.getJob(), job, "Job is not equal for promoted Employee");
        Assertions.assertEquals(employee.getDepartment(), department, "Department is not equal for promoted Employee");
        Assertions.assertEquals(employee.getSalary(), job.getMinSalary(), "Salary is not equal for promoted Employee");
    }
}
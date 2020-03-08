package br.com.hrs.core.model;

import br.com.hrs.core.exception.HrsMandatoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Model Tests - Employee")
public class EmployeeTest {

    private static Employee employee;
    private static Job job;
    private static Department department;

    @BeforeAll
    static void setUp(){
        job = new Job("AR","Architect", 3_000f, 5_000f);
        department = new Department(1, "IT", null);
        employee = new Employee(1,"renato@gmail",1000f, null, null);
    }

    @Test
    @DisplayName("Employee promotion sets job as null but its mandatory")
    public void test01(){

        RuntimeException exception =  Assertions.assertThrows(HrsMandatoryException.class,()-> {
           employee.promotedTo(null, department);
        });

        Assertions.assertTrue(exception.getMessage().matches("Job.*mandatory.*promotion"), String.format("Job mandatory message wrong (%s)", exception.getMessage()));
    }

    @Test
    @DisplayName("Employee promotion sets department as null but its mandatory")
    public void test02(){

        RuntimeException exception =  Assertions.assertThrows(HrsMandatoryException.class,()-> {
            employee.promotedTo(job, null);
        });

        Assertions.assertTrue(exception.getMessage().matches("Department.*mandatory.*promotion"), String.format("Department mandatory message wrong (%s)", exception.getMessage()));
    }

    @Test
    @DisplayName("Promote Employee")
    public void test03(){
        employee.promotedTo(job, department);
        Assertions.assertEquals(job, employee.getJob(), "Job is not equals on promotion");
        Assertions.assertEquals(department, employee.getDepartment(), "Department is not equals on promotion");
        Assertions.assertEquals(job.getMinSalary(), employee.getSalary(), "Salary is not equals on promotion");
        Assertions.assertEquals(Employee.STATUS.PROMOTED, employee.getStatus(), "Status is not equals on promotion");
    }
}

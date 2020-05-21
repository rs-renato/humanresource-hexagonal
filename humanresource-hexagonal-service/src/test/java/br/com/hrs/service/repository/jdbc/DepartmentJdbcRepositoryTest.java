package br.com.hrs.service.repository.jdbc;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Location;
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

@DisplayName("Jdbc Repository - Department")
@ContextConfiguration(classes = HrsJdbcConfiguration.class)
@ExtendWith(SpringExtension.class)
public class DepartmentJdbcRepositoryTest {

    private Integer DEPARTMENT_ID = 12;
    private Integer MANAGER_ID = 100;
    private Integer LOCATION_ID = 15;

    Logger logger = LogManager.getLogger(DepartmentJdbcRepositoryTest.class);

    @Inject
    private Repository<Department, Integer> departmentJdbcRepository;

    @Test
    @DisplayName("Finds for null Department")
    public void test01() {
        Department department = departmentJdbcRepository.find(null);
        Assertions.assertNull(department, "Department should be null");
    }

    @Test
    @DisplayName("Finds for inexistent Department")
    public void test02() {
        Department department = departmentJdbcRepository.find(-1);
        logger.info(department);
        Assertions.assertNull(department, "Department should be null");
    }

    @Test
    @DisplayName("Updates Department")
    public void test03() {
        Department department = departmentJdbcRepository.find(DEPARTMENT_ID);
        logger.info(department);
        department.setName(department.getName() + " altered");
        department.setManager(new Employee.Builder().id(MANAGER_ID).build());
        departmentJdbcRepository.update(department);

        department = departmentJdbcRepository.find(DEPARTMENT_ID);

        Assertions.assertTrue(department.getName().contains("altered"), "Department should be altered");
        Assertions.assertTrue(MANAGER_ID == department.getManager().getId(), "Department manager should be altered");
    }

    @Test
    @DisplayName("Saves Department")
    public void test04() {
        Department department = new Department("new department", new Employee.Builder().id(MANAGER_ID).build(), new Location.Builder().id(LOCATION_ID).build());
        Department departmentSaved = departmentJdbcRepository.save(department);
        Assertions.assertNotNull(departmentSaved, String.format("Department saved should not be null"));
        Department departmentFound = departmentJdbcRepository.find(departmentSaved.getId());
        Assertions.assertEquals(departmentSaved, departmentFound, String.format("Department should be equals"));
    }

    @Test
    @DisplayName("Finds all Departments")
    public void test05() {
        Collection<Department> departments = departmentJdbcRepository.findAll();
        logger.info(departments);
        Assertions.assertNotNull(departments, "Departments should be listed");
        Assertions.assertTrue(departments.size() >= 27, "Departments should be listed at all");
    }

    @Test
    @DisplayName("Finds for Department")
    public void test06() {
        Department department = departmentJdbcRepository.find(DEPARTMENT_ID);
        logger.info(department);
        Assertions.assertNotNull(department, "Department should not be null");
    }

    @Test
    @DisplayName("Department exists")
    public void test07() {
        boolean exists = departmentJdbcRepository.exists(DEPARTMENT_ID);
        Assertions.assertTrue(exists, "Department should be existent");
    }

    @Test
    @DisplayName("Department doesn't exists")
    public void test08() {
        boolean exists = departmentJdbcRepository.exists(-1);
        Assertions.assertFalse(exists, "Department should not be existent");
    }

    @Test
    @DisplayName("Deletes Department")
    public void test09() {
        departmentJdbcRepository.delete(DEPARTMENT_ID);
        Department department = departmentJdbcRepository.find(DEPARTMENT_ID);
        Assertions.assertNull(department, "Department still existing");
    }
}
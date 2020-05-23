package br.com.hrs.service.repository.jpa;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.service.repository.config.HrsJpaConfiguration;
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

@DisplayName("Jpa Repository - Department")
@ContextConfiguration(classes = HrsJpaConfiguration.class)
@ExtendWith(SpringExtension.class)
public class DepartmentJpaRepositoryTest {

    private static final int DEPARTMENT_ID = 1;
    private static int DEPARTMENT_SAVED_ID;
    private Employee manager = new Employee.Builder().id(2).build();
    private Location location = new Location.Builder().id(2).build();


    Logger logger = LogManager.getLogger(DepartmentJpaRepositoryTest.class);

    @Inject
    private Repository<Department, Integer> departmentJpaRepository;

    @Test
    @DisplayName("Finds for inexistent Department")
    public void test01() {
        Department department = departmentJpaRepository.find(-1);
        logger.info(department);
        Assertions.assertNull(department, "Department should be null");
    }

    @Test
    @DisplayName("Updates Department")
    public void test02() {
        Department department = departmentJpaRepository.find(DEPARTMENT_ID);
        logger.info(department);
        department.setName(department.getName() + " altered");
        Employee manager = new Employee.Builder().id(2).build();
        department.setManager(manager);

        departmentJpaRepository.update(department);

        Department departmentSaved  = departmentJpaRepository.find(DEPARTMENT_ID);

        Assertions.assertEquals(departmentSaved.getName(), department.getName(), "Department name should be altered");
        Assertions.assertEquals(departmentSaved.getManager().getId(), manager.getId(), "Department manager should be altered");
    }

    @Test
    @DisplayName("Saves Department")
    public void test03() {

        Department department = new Department("new depertment", manager, location);
        Department departmentSaved = departmentJpaRepository.save(department);

        DEPARTMENT_SAVED_ID = departmentSaved.getId();

        department = departmentJpaRepository.find(departmentSaved.getId());

        Assertions.assertNotNull(department, "Department should be saved");
        Assertions.assertEquals(department, departmentSaved, "Department saved should be equals");
        Assertions.assertEquals(manager, department.getManager(), "Department manager should be equals");
        Assertions.assertEquals(location, department.getLocation(), "Region location should be equals");
    }

    @Test
    @DisplayName("Finds all departments")
    public void test04() {
        Collection<Department> departments = departmentJpaRepository.findAll();
        logger.info(departments);
        Assertions.assertNotNull(departments, "departments should be listed");
        Assertions.assertTrue(departments.size() >= 25, "departments should be listed at all");

    }

    @Test
    @DisplayName("Finds for Department")
    public void test05() {
        Department department = departmentJpaRepository.find(DEPARTMENT_ID);
        logger.info(department);
        Assertions.assertNotNull(department, "Department should not be null");
    }

    @Test
    @DisplayName("Deletes Department")
    public void test06() {
        Department department = departmentJpaRepository.find(DEPARTMENT_SAVED_ID);
        departmentJpaRepository.delete(department.getId());
        boolean exists = departmentJpaRepository.exists(department.getId());
        Assertions.assertFalse(exists, "Department still existing");
    }

    @Test
    @DisplayName("Department exists")
    public void test07() {
        boolean exists = departmentJpaRepository.exists(DEPARTMENT_ID);
        Assertions.assertTrue(exists, "Department should be existent");
    }

    @Test
    @DisplayName("Department doesn't exists")
    public void test08() {
        boolean exists = departmentJpaRepository.exists(DEPARTMENT_SAVED_ID);
        Assertions.assertFalse(exists, "Department should not be existent");
    }
}